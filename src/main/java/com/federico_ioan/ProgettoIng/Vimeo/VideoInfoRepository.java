package com.federico_ioan.ProgettoIng.Vimeo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoInfoRepository extends JpaRepository<VideoInfo, Long> {
    VideoInfo findVideoInfoByUrl(String url);
}
