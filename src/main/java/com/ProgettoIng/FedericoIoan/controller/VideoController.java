package com.ProgettoIng.FedericoIoan.controller;

import com.ProgettoIng.FedericoIoan.model.VideoDetails;
import com.ProgettoIng.FedericoIoan.service.VideoDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/courses/{courseId}/modules/{moduleId}/videos")
public class VideoController {

    @Autowired
    private VideoDetailsServiceImpl videoDetailsService;

    @PostMapping
    public ResponseEntity<VideoDetails> uploadVideo(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        VideoDetails videoDetails = videoDetailsService.uploadVideo(id, file);
        return ResponseEntity.ok(videoDetails);
    }

    @GetMapping
    public ResponseEntity<List<VideoDetails>> findVideos(@PathVariable Long moduleId) {
        List<VideoDetails> videoDetailsList = videoDetailsService.findVideos(moduleId);
        return ResponseEntity.ok(videoDetailsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoDetails> findVideo(@PathVariable Long id) {
        VideoDetails videoDetails = videoDetailsService.findVideo(id);
        return ResponseEntity.ok(videoDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VideoDetails> deleteVideo(@PathVariable Long id) {
        VideoDetails videoDetails = videoDetailsService.deleteVideo(id);
        return ResponseEntity.ok(videoDetails);
    }
}
