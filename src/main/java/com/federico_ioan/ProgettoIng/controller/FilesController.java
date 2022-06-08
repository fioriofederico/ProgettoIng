package com.federico_ioan.ProgettoIng.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


import com.federico_ioan.ProgettoIng.service.FileService;
import com.federico_ioan.ProgettoIng.service.IService.FilesStorageService;
import com.federico_ioan.ProgettoIng.model.dto.MessageResponse;
import com.federico_ioan.ProgettoIng.model.FileInfo;
import com.federico_ioan.ProgettoIng.repository.FileInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/files")
@CrossOrigin("http://localhost:8081")
public class FilesController {

  @Autowired
  private FileService fileService;


  @PostMapping()
  public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) {
    return fileService.uploadFile(file);
  }

  // TODO: add delete file API function

  @GetMapping()
  public ResponseEntity<List<FileInfo>> getFileInfos() {
   return fileService.getFileInfos();
  }

  @GetMapping("/{url}")
  public ResponseEntity<Resource> getFile(@PathVariable String url) {
   return fileService.getFile(url);
  }
}
