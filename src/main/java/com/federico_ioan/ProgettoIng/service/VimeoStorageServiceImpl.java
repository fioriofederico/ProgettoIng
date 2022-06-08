package com.federico_ioan.ProgettoIng.service;

import com.federico_ioan.ProgettoIng.utils.Vimeo;
import com.federico_ioan.ProgettoIng.service.IService.VimeoStorageService;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class VimeoStorageServiceImpl implements VimeoStorageService {


  private Path root = Paths.get("uploadsVideo");

  @Override
  public void init() {
    try {
      Files.createDirectory(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public void save(MultipartFile file, String filename, String subDirectory) {
    try {
        Path Folder = Files.createDirectory(Path.of(subDirectory));
        Files.copy(file.getInputStream(), Folder.resolve(filename));
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll(String subDirectory) {
    Path subFolder = Paths.get(subDirectory);
    FileSystemUtils.deleteRecursively(subFolder.toFile());
  }


@Override
public <S extends Vimeo> S save(S entity) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void delete(Vimeo entity) {
  // TODO Auto-generated method stub
	
}


@Override
public void deleteAll(Iterable<? extends Vimeo> entities) {
  // TODO Auto-generated method stub
	
}

}
