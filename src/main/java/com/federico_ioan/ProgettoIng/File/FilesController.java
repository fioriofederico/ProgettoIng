package com.federico_ioan.ProgettoIng.File;

import java.security.MessageDigest;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


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

    String message = "";
    try {
      LocalDateTime localDateTime  = LocalDateTime.now(ZoneId.of("GMT+01:00"));
      String fileName = file.getOriginalFilename();
      String stringToHash = fileName + localDateTime.toString();

      // Hashing the filename
      byte[] bytesOfMessage = stringToHash.getBytes("UTF-8");
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] theMD5digest = md.digest(bytesOfMessage);

      String newFileName = theMD5digest.toString() + "." + file.getOriginalFilename().split("\\.")[1];

      storageService.save(file, newFileName);

      // Save into the db
      FileInfo fileInfo = new FileInfo(fileName, newFileName, localDateTime);
      fileInfoRepository.save(fileInfo);


      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/files")
  public ResponseEntity<List<FileInfo>> getListFiles() {
    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder .fromMethodName(FilesController.class, "getFile",
              path.getFileName().toString()).build().toString();

      return new FileInfo(filename, url, null);
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
