package com.ProgettoIng.FedericoIoan.controller;

import com.ProgettoIng.FedericoIoan.model.FileInfo;
import com.ProgettoIng.FedericoIoan.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@RestController
@RequestMapping("/files")
@CrossOrigin("http://localhost:8081")
public class FilesController {

  @Autowired
  private FileService fileService;

  @GetMapping()
  public ResponseEntity<List<FileInfo>> getFileInfos() {
    return fileService.getFileInfos();
  }

  @GetMapping("/{url}")
  public ResponseEntity<Resource> getFile(@PathVariable String url) {
    return fileService.getFile(url);
  }

  @PostMapping()
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    return fileService.uploadFile(file);
  }

  // TODO: add delete file API function

}
