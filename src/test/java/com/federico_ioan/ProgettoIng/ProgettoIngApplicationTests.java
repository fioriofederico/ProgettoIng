package com.federico_ioan.ProgettoIng;

import javax.annotation.Resource;

import com.federico_ioan.ProgettoIng.Vimeo.VimeoStorageService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import com.federico_ioan.ProgettoIng.File.FilesStorageService;

@SpringBootTest
class ProgettoIngApplicationTests implements CommandLineRunner {

	@Test
	void contextLoads() {
	}
	
	@Resource
	  FilesStorageService storageService;

	  public static void main(String[] args) {
	    SpringApplication.run(ProgettoIngApplication.class, args);
	  }

	  @Override
	  public void run(String... arg) throws Exception {
	    storageService.deleteAll();
	    storageService.init();
	  }

}
