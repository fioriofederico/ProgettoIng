package com.federico_ioan.ProgettoIng.Video;

import org.springframework.data.jpa.repository.JpaRepository;


public interface VideoRepository extends JpaRepository<Video, Long> {
   Video findFileInfoByUrl(String url);
}
