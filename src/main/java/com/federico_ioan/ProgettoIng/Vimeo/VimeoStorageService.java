package com.federico_ioan.ProgettoIng.Vimeo;

import org.springframework.web.multipart.MultipartFile;

public interface VimeoStorageService {

	void init();

	public void save(MultipartFile file, String filename);

	  public void deleteAll();

    <S extends Vimeo> S save(S entity);

	void delete(Vimeo entity);

	void deleteAll(Iterable<? extends Vimeo> entities);
}
