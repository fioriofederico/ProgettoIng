package com.federico_ioan.ProgettoIng.repository;

import com.federico_ioan.ProgettoIng.model.dto.VideoInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoInfoRepository extends JpaRepository<VideoInfo, Long> {
    VideoInfo findVideoInfoByUrl(String url);

    VideoInfo findEndPointVimeoById(Long videoId);
}
