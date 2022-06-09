package com.ProgettoIng.FedericoIoan.repository;

import com.ProgettoIng.FedericoIoan.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
   FileInfo findFileInfoByUrl(String url);
}
