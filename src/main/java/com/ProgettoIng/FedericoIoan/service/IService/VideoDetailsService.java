package com.ProgettoIng.FedericoIoan.service.IService;

import com.ProgettoIng.FedericoIoan.model.VideoDetails;
import org.springframework.web.multipart.MultipartFile;

public interface VideoDetailsService {

    VideoDetails uploadVideo(Long courseModuleId, MultipartFile file);

    VideoDetails deleteVideo(Long id);
}
