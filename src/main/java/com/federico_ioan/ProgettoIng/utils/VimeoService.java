package com.federico_ioan.ProgettoIng.utils;

import com.federico_ioan.ProgettoIng.config.VimeoException;
import com.federico_ioan.ProgettoIng.model.dto.MessageResponse;
import com.federico_ioan.ProgettoIng.model.dto.VideoInfo;
import com.federico_ioan.ProgettoIng.model.dto.VimeoResponse;
import com.federico_ioan.ProgettoIng.repository.VideoInfoRepository;
import com.federico_ioan.ProgettoIng.service.IService.VimeoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Service
public class VimeoService {

    @Autowired
    private VimeoStorageService storageService;

    @Autowired
    private VideoInfoRepository videoInfoRepository;


    //Token per l'accesso alle api
    private Vimeo vimeo = new Vimeo("33cf31f02fad348a5ff79f204c215ce7");


    public ResponseEntity<MessageResponse> uploadVideo(MultipartFile file, String description)  {
        // Initilize the response message
        String message = "";
        //Genera Nome Folder Casuale
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 40;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        //Quando procedo al savattaggio creo cartella la cartella e salvo il file al suo interno
        storageService.save(file, file.getOriginalFilename(), generatedString);
        Path variabile = Paths.get(generatedString);
        //File path = new File(this.root.toFile().getAbsolutePath() + "/" + generatedString + "/" + file.getOriginalFilename());
        File path = new File(variabile.toFile().getAbsolutePath()  + "/" + file.getOriginalFilename());
        String videoEndPoint = null;
        try {
            videoEndPoint = this.vimeo.addVideo(path, true);
        } catch (IOException | VimeoException e) {
            throw new RuntimeException(e);
        }
        //Rimuovo cartella dell'archiviazione video
        storageService.deleteAll(generatedString);
        //get video info
        VimeoResponse info = null;
        try {
            info = this.vimeo.getVideoInfo(videoEndPoint);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String linkVideo = info.getJson().getString("link").toString();
        VideoInfo videoInfo = new VideoInfo(file.getOriginalFilename(), linkVideo, videoEndPoint, description);
        videoInfoRepository.save(videoInfo);
        message = "Uploaded the file successfully: " + linkVideo;
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
    }

    public ResponseEntity<?> deleteVideo(Long videoId) {

        try {
            VideoInfo videoInfo = videoInfoRepository.findById(videoId).orElseThrow();
            String endPoint = videoInfo.getEndPointVimeo().toString();
            vimeo.removeVideo(endPoint);
            videoInfoRepository.delete(videoInfo);
            return ResponseEntity.status(HttpStatus.OK).body("Video deleted");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> getVideos(){
        return ResponseEntity.ok(videoInfoRepository.findAll());
    }
    public ResponseEntity<VideoInfo> getVideo(Long videoId){
        try {
            return ResponseEntity.ok(videoInfoRepository.findById(videoId).orElseThrow(Exception::new));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<VideoInfo> updateCourse(@PathVariable Long videoId, @RequestBody VideoInfo videoInfoDto) throws IOException {
        VideoInfo videoInfoToUpdate = videoInfoRepository.findById(videoId).orElseThrow();
        if(videoInfoDto.getName()!= null) {
            videoInfoToUpdate.setName(videoInfoDto.getName());
        }if(videoInfoDto.getDescription()!= null){
            videoInfoToUpdate.setDescription(videoInfoDto.getDescription());
        }
        return ResponseEntity.ok(videoInfoRepository.save(videoInfoToUpdate));
    }
}
