package com.federico_ioan.ProgettoIng.File;

import java.io.File;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

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


@Controller
@CrossOrigin("http://localhost:8081")
@RestController
public class FilesController {

  private final FileInfoRepository fileInfoRepository;

  @Autowired
  FilesStorageService storageService;

  public FilesController(FileInfoRepository fileInfoRepository) {
    this.fileInfoRepository = fileInfoRepository;
  }

  @PostMapping("/files")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {

    // Initilize the response message
    String message = "";
    LocalDateTime localDateTime  = LocalDateTime.now(ZoneId.of("GMT+01:00"));
    String fileName = file.getOriginalFilename();
    // Create a url to hash
    String urlToHash = fileName + Timestamp.valueOf(localDateTime);

    try {

      //FileInfo fileInfo = new FileInfo(null, null, localDateTime);

      // Hash the url
      byte[] bytesOfUrl = urlToHash.getBytes("UTF-8");
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digestedUrl = md.digest(bytesOfUrl);

      // Build the final url
      String fileUrl = digestedUrl.toString() + "." + file.getOriginalFilename().split("\\.")[1];

      // Save file in the server
      storageService.save(file, fileUrl);

      // Save file info in the db
      FileInfo fileInfo = new FileInfo(fileName, fileUrl, localDateTime);
      fileInfoRepository.save(fileInfo);

      // Return response message
      message = "Uploaded the file successfully: " + fileName;
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + fileName + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  // TO DO: add delete file API function


  @GetMapping("/files")
  public ResponseEntity<List<FileInfo>> getListFiles() {
    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {   // TO DO: get FileInfo from db
      List<FileInfo> fileInfoList = fileInfoRepository.findByUrl(path.getFileName().toString());
      String url = MvcUriComponentsBuilder.fromMethodName(FilesController.class, "getFile",
              path.getFileName().toString()).build().toString();
      LocalDateTime localDateTime  = LocalDateTime.now(ZoneId.of("GMT+01:00"));
      return new FileInfo(fileInfoList.stream().findFirst().get().getName(), url, localDateTime);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @GetMapping("/files/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }
}
