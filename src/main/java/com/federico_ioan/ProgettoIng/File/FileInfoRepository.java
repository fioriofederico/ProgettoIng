package com.federico_ioan.ProgettoIng.File;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface FileInfoRepository extends CrudRepository<FileInfo, Long> {
    List<FileInfo> findByUrl(String Url);
}
