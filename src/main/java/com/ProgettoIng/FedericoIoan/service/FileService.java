package com.ProgettoIng.FedericoIoan.service;

import com.ProgettoIng.FedericoIoan.model.FileInfo;
import com.ProgettoIng.FedericoIoan.repository.FileInfoRepository;
import com.ProgettoIng.FedericoIoan.service.IService.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

// TODO: rinominare FileServiceImpl e aggiungere FIleService

@Service
public class FileService {

    @Autowired
    FileInfoRepository fileInfoRepository;

    @Value("${federico_ioan.app.BackEndUrl}")
    private String BackEndUrl;

    @Autowired
    FilesStorageService storageService;

    public ResponseEntity<String> uploadFile(MultipartFile file) {

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
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + fileName + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    public ResponseEntity<List<FileInfo>> getFileInfos() {
        List<FileInfo> fileInfos = fileInfoRepository.findAll();
        fileInfos.forEach( (fileInfo -> {
            fileInfo.setUrl(this.BackEndUrl + "/files/" + fileInfo.getUrl());
        }));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION).body(fileInfos);
    }

    public ResponseEntity<Resource> getFile(String url) {
        Resource file = storageService.load(url);
        FileInfo fileInfo = fileInfoRepository.findFileInfoByUrl(url);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileInfo.getName() + "\"").body(file);
    }

}