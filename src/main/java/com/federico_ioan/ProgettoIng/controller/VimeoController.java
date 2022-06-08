package com.federico_ioan.ProgettoIng.controller;

import com.federico_ioan.ProgettoIng.config.VimeoException;
import com.federico_ioan.ProgettoIng.model.dto.MessageResponse;
import com.federico_ioan.ProgettoIng.model.dto.VideoInfo;
import com.federico_ioan.ProgettoIng.utils.VimeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/video")
@CrossOrigin("http://localhost:8081")
public class VimeoController {

    @Autowired
    private VimeoService vimeoService;

    @PostMapping()
    public ResponseEntity<MessageResponse> uploadVideo(@RequestParam("file") MultipartFile file, @Param("description") String description) throws IOException, VimeoException, InterruptedException {
        return vimeoService.uploadVideo(file, description);
    }

    @GetMapping()
    public ResponseEntity<?> getVideos(){
       return vimeoService.getVideos();
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<VideoInfo> getVideo(@PathVariable Long videoId){
    return vimeoService.getVideo(videoId);
    }

    @PutMapping("/{videoId}")
    public ResponseEntity<VideoInfo> updateCourse(@PathVariable Long videoId, @RequestBody VideoInfo videoInfoDto) throws IOException {
      return vimeoService.updateCourse(videoId, videoInfoDto);
    }

    @DeleteMapping("/{videoId}")
    ResponseEntity<?> deleteVideo(@PathVariable Long videoId) throws IOException {
    return vimeoService.deleteVideo(videoId);
    }

}

