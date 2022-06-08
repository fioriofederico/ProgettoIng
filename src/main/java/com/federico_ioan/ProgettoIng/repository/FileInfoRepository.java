package com.federico_ioan.ProgettoIng.repository;

import com.federico_ioan.ProgettoIng.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
   FileInfo findFileInfoByUrl(String url);
}
