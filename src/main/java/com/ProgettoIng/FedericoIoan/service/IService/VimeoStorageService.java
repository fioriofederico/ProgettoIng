package com.ProgettoIng.FedericoIoan.service.IService;

import com.ProgettoIng.FedericoIoan.utils.Vimeo;
import org.springframework.web.multipart.MultipartFile;

public interface VimeoStorageService {

//	void init();

	void save(MultipartFile file, String filename, String subDirectory);

	void deleteAll(String subDirectory);

//    <S extends Vimeo> S save(S entity);
//
//	void delete(Vimeo entity);
//
//	void deleteAll(Iterable<? extends Vimeo> entities);
}
