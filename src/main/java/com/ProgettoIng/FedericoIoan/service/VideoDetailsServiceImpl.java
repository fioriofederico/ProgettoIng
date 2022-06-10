package com.ProgettoIng.FedericoIoan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ProgettoIng.FedericoIoan.model.CourseModule;
import com.ProgettoIng.FedericoIoan.model.VideoDetails;
import com.ProgettoIng.FedericoIoan.repository.CourseModuleRepository;
import com.ProgettoIng.FedericoIoan.repository.VideoDetailsRepository;
import com.ProgettoIng.FedericoIoan.service.IService.VideoDetailsService;
import org.springframework.web.multipart.MultipartFile;
import com.clickntap.vimeo.*;

import java.io.File;
import java.io.IOException;


@Service
public class VideoDetailsServiceImpl implements VideoDetailsService {

    private final Vimeo vimeo = new Vimeo("33cf31f02fad348a5ff79f204c215ce7");

    private String subDirectory = "videos";

    @Autowired
    private CourseModuleRepository courseModuleRepository;

    @Autowired
    private VideoDetailsRepository videoDetailsRepository;

    @Autowired
    private VimeoStorageServiceImpl vimeoStorageService;

    public VideoDetails uploadVideo(Long courseModuleId, MultipartFile file) {

        // Check if course module exists
        if (courseModuleRepository.existsById(courseModuleId))
            throw new IllegalArgumentException("Course module not found");

        // Check if file is not empty
        if (file.isEmpty())
            throw new IllegalArgumentException("File is empty");

        // Save video to subdirectory
        File video = vimeoStorageService.save(file, subDirectory);

        try {
            // Upload video to Vimeo
            String videoEndPoint = vimeo.addVideo(video, true);

            // Get video info from Vimeo
            VimeoResponse videoInfo = vimeo.getVideoInfo(videoEndPoint);

            // Edit video metadata
            String name = file.getOriginalFilename();
            String description = "Test Description";
            String license = ""; //see Vimeo API Documentation
            String privacyView = "disable"; //see Vimeo API Documentation
            String privacyEmbed = "whitelist"; //see Vimeo API Documentation
            vimeo.updateVideoMetadata(videoEndPoint, name, description, license, privacyView, privacyEmbed, false);

            // Create VideoDetails object
            VideoDetails videoDetails = new VideoDetails();

            // Set video details properties
            videoDetails.setName(file.getOriginalFilename());
            videoDetails.setDescription(description);
            videoDetails.setUrl(videoInfo.getJson().getString("link"));
            videoDetails.setEndPointVimeo(videoEndPoint);

            CourseModule courseModule = courseModuleRepository.findById(courseModuleId).get();
            videoDetails.setCourseModule(courseModule);

            // Save video details
            return videoDetailsRepository.save(videoDetails);

        } catch (IOException | VimeoException e) {
            e.printStackTrace();
        }

        // Delete video from subdirectory even if upload failed
        vimeoStorageService.deleteAll(subDirectory);

        return null;
    }

    public VideoDetails deleteVideo(Long id) {
        try {

            // Get video details
            VideoDetails videoDetails = videoDetailsRepository.findById(id).orElseThrow(Exception::new);
            String videoEndPoint = videoDetails.getEndPointVimeo();

            // Delete video from Vimeo using Vimeo API
            vimeo.removeVideo(videoEndPoint);

            // Delete video details from database
            videoDetailsRepository.delete(videoDetails);

            return videoDetails;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
