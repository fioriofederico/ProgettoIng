package com.ProgettoIng.FedericoIoan.service.IService;

import org.springframework.core.io.Resource;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.web.multipart.MultipartFile;


public interface StorageService {

    void init();

    void save(MultipartFile file, String filename);

    Resource load(String filename);

    void delete(String filename);

    void deleteAll();
}
