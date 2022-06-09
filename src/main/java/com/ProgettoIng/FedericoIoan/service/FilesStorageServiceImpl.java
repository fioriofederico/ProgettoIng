package com.ProgettoIng.FedericoIoan.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

import com.ProgettoIng.FedericoIoan.model.FileInfo;
import com.ProgettoIng.FedericoIoan.service.IService.FilesStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  private final Path root = Paths.get("uploads");

  @Override
  public void init() {
    try {
      Files.createDirectory(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public void save(MultipartFile file, String filename) {
    try {
    	Files.copy(file.getInputStream(), this.root.resolve(filename));
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  @Override
  public Resource load(String filename) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
  }

//  @Override
//  public Stream<Path> loadAll() {
//    try {
//      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
//    } catch (IOException e) {
//      throw new RuntimeException("Could not load the files!");
//    }
//  }

//@Override
//public <S extends FileInfo> S save(S entity) {
//	// TODO Auto-generated method stub
//	return null;
//}
//
//@Override
//public <S extends FileInfo> Iterable<S> saveAll(Iterable<S> entities) {
//  // TODO Auto-generated method stub
//	return null;
//}
//
//@Override
//public Optional<FileInfo> findById(Long id) {
//	// TODO Auto-generated method stub
//	return null;
//}
//
//@Override
//public boolean existsById(Long id) {
//	// TODO Auto-generated method stub
//	return false;
//}
//
//@Override
//public Iterable<FileInfo> findAll() {
//	// TODO Auto-generated method stub
//	return null;
//}
//
//@Override
//public Iterable<FileInfo> findAllById(Iterable<Long> ids) {
//  // TODO Auto-generated method stub
//	return null;
//}
//
//@Override
//public long count() {
//	// TODO Auto-generated method stub
//	return 0;
//}
//
//@Override
//public void deleteById(Long id) {
//	// TODO Auto-generated method stub
//
//}
//
//@Override
//public void delete(FileInfo entity) {
//  // TODO Auto-generated method stub
//
//}
//
//@Override
//public void deleteAllById(Iterable<? extends Long> ids) {
//  // TODO Auto-generated method stub
//
//}
//
//@Override
//public void deleteAll(Iterable<? extends FileInfo> entities) {
//  // TODO Auto-generated method stub
//
//}

}
