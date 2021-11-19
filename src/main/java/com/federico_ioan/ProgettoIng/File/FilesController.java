package com.federico_ioan.ProgettoIng.File;

import java.time.Instant;
import java.time.LocalDateTime;
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

import com.federico_ioan.ProgettoIng.File.FilesController;

import io.opencensus.common.Timestamp;



@Controller
@CrossOrigin("http://localhost:8081")
@RestController
public class FilesController {

	/*private final FilesStorageService fileRepository;
	
	FilesController(FilesStorageService repository){
		fileRepository = repository; 
	}*/
	
  @Autowired
  FilesStorageService storageService;
  
  @PostMapping("/files")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
  //public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id, @RequestParam("dateInsert")LocalDateTime dateInsert) {
 
    String message = "";
    try {
      storageService.save(file);
      
      //storageService.save()
      //message = "Uploaded the file successfully: " + file.getOriginalFilename() + file.getName() + file.getSize() + id.intValue() + dateInsert.get(null);
      //Timestamp ts = Timestamp.from(Instant.now());
      message = "Uploaded the file successfully: " + file.getOriginalFilename() + "  File name: " + file.getName() +"  Content Typy:" + file.getContentType() + " Ora Attuale ";
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
      String url = MvcUriComponentsBuilder
          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

      return new FileInfo(null, filename, url, null);
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
