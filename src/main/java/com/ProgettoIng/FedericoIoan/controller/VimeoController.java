package com.ProgettoIng.FedericoIoan.controller;

import com.ProgettoIng.FedericoIoan.model.dto.VideoInfo;
import com.ProgettoIng.FedericoIoan.utils.VimeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/video")
@CrossOrigin("http://localhost:8081")
public class VimeoController {

    @Autowired
    private VimeoService vimeoService;

    @PostMapping()
    public ResponseEntity<?> uploadVideo(@RequestParam("file") MultipartFile file, @Param("description") String description) {
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
    public ResponseEntity<VideoInfo> updateCourse(@PathVariable Long videoId, @RequestBody VideoInfo videoInfoDto) {
      return vimeoService.updateCourse(videoId, videoInfoDto);
    }

    @DeleteMapping("/{videoId}")
    ResponseEntity<?> deleteVideo(@PathVariable Long videoId) {
    return vimeoService.deleteVideo(videoId);
    }

}

