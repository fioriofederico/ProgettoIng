package com.ProgettoIng.FedericoIoan.repository;

import com.ProgettoIng.FedericoIoan.model.dto.VideoInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoInfoRepository extends JpaRepository<VideoInfo, Long> {
    VideoInfo findVideoInfoByUrl(String url);
    VideoInfo findEndPointVimeoById(Long videoId);
}
