package com.federico_ioan.ProgettoIng.File;

import org.springframework.data.jpa.repository.JpaRepository;


public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
   FileInfo findFileInfoByUrl(String url);
}
