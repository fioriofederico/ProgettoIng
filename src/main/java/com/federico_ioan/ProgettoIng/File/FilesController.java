package com.federico_ioan.ProgettoIng.File;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.federico_ioan.ProgettoIng.CourseModule.CourseModule;
import com.federico_ioan.ProgettoIng.Payload.Response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/files")
@CrossOrigin("http://localhost:8081")
public class FilesController {

  private final FileInfoRepository fileInfoRepository;

  @Autowired
  FilesStorageService storageService;

  public FilesController(FileInfoRepository fileInfoRepository) {
    this.fileInfoRepository = fileInfoRepository;
  }

  @PostMapping()
  public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) {

    // Initilize the response message
    String message = "";
    LocalDateTime localDateTime  = LocalDateTime.now(ZoneId.of("GMT+01:00"));
    String fileName = file.getOriginalFilename();
    // Create a url to hash
    String urlToHash = fileName + Timestamp.valueOf(localDateTime);

    try {
      // Hash the url
      byte[] bytesOfUrl = urlToHash.getBytes(StandardCharsets.UTF_8);
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digestedUrl = md.digest(bytesOfUrl);

      // Build the final url
      String fileUrl = digestedUrl.toString().replaceAll("[^a-zA-Z0-9]", "");

      // Save file in the server
      storageService.save(file, fileUrl);

      // Save file info in the db
      FileInfo fileInfo = new FileInfo(fileName, fileUrl);
      fileInfoRepository.save(fileInfo);

      // Return response message
      message = "Uploaded the file successfully: " + fileName;
      return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + fileName + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
    }
  }

  // TO DO: add delete file API function

  @GetMapping()
  public ResponseEntity<List<FileInfo>> getFileInfos() {
//    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {   // TO DO: get FileInfo from db
//      List<FileInfo> fileInfoList = fileInfoRepository.findByUrl(path.getFileName().toString());
//      String url = MvcUriComponentsBuilder.fromMethodName(FilesController.class, "getFile",
//              path.getFileName().toString()).build().toString();
//      LocalDateTime localDateTime  = LocalDateTime.now(ZoneId.of("GMT+01:00"));
//      return new FileInfo(fileInfoList.stream().findFirst().get().getName(), url);
//    }).collect(Collectors.toList());
    List<FileInfo> fileInfos = (List<FileInfo>) fileInfoRepository.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @GetMapping("/{filename}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);
    FileInfo fileInfo = fileInfoRepository.findByUrl(filename).stream().findFirst().get();
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileInfo.getName() + "\"").body(file);
  }
}
