package com.ProgettoIng.FedericoIoan.service.IService;

import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

import com.ProgettoIng.FedericoIoan.model.FileInfo;
import org.springframework.core.io.Resource;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService  {

	void init();

	void save(MultipartFile file, String filename);

	Resource load(String filename);

	void deleteAll();

//	Stream<Path> loadAll();
//
//    <S extends FileInfo> S save(S entity);
//
//	<S extends FileInfo> Iterable<S> saveAll(Iterable<S> entities);
//
//	Optional<FileInfo> findById(Long id);
//
//	boolean existsById(Long id);
//
//	Iterable<FileInfo> findAll();
//
//	Iterable<FileInfo> findAllById(Iterable<Long> ids);
//
//	long count();
//
//	void deleteById(Long id);
//
//	void delete(FileInfo entity);
//
//	void deleteAllById(Iterable<? extends Long> ids);
//
//	void deleteAll(Iterable<? extends FileInfo> entities);
}
