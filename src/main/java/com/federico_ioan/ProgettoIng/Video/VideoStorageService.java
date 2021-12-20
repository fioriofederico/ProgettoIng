package com.federico_ioan.ProgettoIng.Video;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public interface VideoStorageService {
	  public void init();

	  public void save(MultipartFile file, String filename);

	  public Resource load(String filename);

	  public void deleteAll();

	  public Stream<Path> loadAll();

    <S extends Video> S save(S entity);

	<S extends Video> Iterable<S> saveAll(Iterable<S> entities);

	Optional<Video> findById(Long id);

	boolean existsById(Long id);

	Iterable<Video> findAll();

	Iterable<Video> findAllById(Iterable<Long> ids);

	long count();

	void deleteById(Long id);

	void delete(Video entity);

	void deleteAllById(Iterable<? extends Long> ids);

	void deleteAll(Iterable<? extends Video> entities);
}
