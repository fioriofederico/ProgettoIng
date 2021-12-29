package com.federico_ioan.ProgettoIng.Vimeo;

import com.federico_ioan.ProgettoIng.Payload.Response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;


@RestController
@RequestMapping("/video")
@CrossOrigin("http://localhost:8081")


public class VimeoController {

    private final VideoInfoRepository videoInfoRepository;

    @Autowired
    VimeoStorageService storageService;

    public VimeoController(VideoInfoRepository videoInfoRepository){
        this.videoInfoRepository = videoInfoRepository;
    }

    //Token per l'accesso alle api
    private Vimeo vimeo = new Vimeo("33cf31f02fad348a5ff79f204c215ce7");

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadVideo(@RequestParam("file") MultipartFile file) throws IOException, VimeoException, InterruptedException {
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
        String videoEndPoint = this.vimeo.addVideo(path, true);
        //Rimuovo cartella dell'archiviazione video
        storageService.deleteAll(generatedString);
        //get video info
        VimeoResponse info = this.vimeo.getVideoInfo(videoEndPoint);
        String linkVideo = info.getJson().getString("link").toString();
        HashMap<String, String> results = new HashMap<>();
        results.put("name", file.getOriginalFilename().toString());
        results.put("url", linkVideo);
        System.out.println(results);
        VideoInfo videoInfo = new VideoInfo(file.getOriginalFilename(), linkVideo);
        videoInfoRepository.save(videoInfo);
        //vimeo.updateVideoMetadata(videoEndPoint, name, desc, license, privacyView, privacyEmbed, reviewLink);
        message = "Uploaded the file successfully: " + linkVideo;
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
    }
    /*
    //edit video
    String name = "Name";
    String desc = "Description";
    String license = ""; //see Vimeo API Documentation
    String privacyView = "disable"; //see Vimeo API Documentation
    String privacyEmbed = "whitelist"; //see Vimeo API Documentation
    boolean reviewLink = false;
    vimeo.updateVideoMetadata(videoEndPoint, name, desc, license, privacyView, privacyEmbed, reviewLink);

    //add video privacy domain
    vimeo.addVideoPrivacyDomain(videoEndPoint, "clickntap.com");

    //delete video
    vimeo.removeVideo(videoEndPoint);
    */
}

