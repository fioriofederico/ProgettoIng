package com.ProgettoIng.FedericoIoan;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import com.ProgettoIng.FedericoIoan.service.IService.StorageService;

@SpringBootTest
class ProgettoIngApplicationTests implements CommandLineRunner {

	@Test
	void contextLoads() {
	}
	
	@Resource
    StorageService storageService;

	  public static void main(String[] args) {
	    SpringApplication.run(ProgettoIngApplication.class, args);
	  }

	  @Override
	  public void run(String... arg) throws Exception {
	    storageService.deleteAll();
	    storageService.init();
	  }

}
