package com.federico_ioan.ProgettoIng.Video;
import com.federico_ioan.ProgettoIng.Payload.Response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;





@RestController
@RequestMapping("/video")
@CrossOrigin("http://localhost:8081")
public class VideoController {

  private final VideoRepository videoRepository;

  @Value("${federico_ioan.app.BackEndUrl}")
  private String BackEndUrl;

  @Autowired
  VideoStorageService storageService;

  public VideoController(VideoRepository videoRepository) {
    this.videoRepository = videoRepository;
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
      Video video = new Video(fileName, fileUrl);
      videoRepository.save(video);


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
  public ResponseEntity<List<Video>> getFileInfos() {
    List<Video> videos = videoRepository.findAll();
    videos.forEach( (video -> {
      video.setUrl(this.BackEndUrl + "/video/" + video.getUrl());
    }));
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION).body(videos);
  }

  @GetMapping("/{url}")
  public ResponseEntity<Resource> getFile(@PathVariable String url) {
    Resource file = storageService.load(url);
    Video video = videoRepository.findFileInfoByUrl(url);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + video.getName() + "\"").body(file);
  }
}
