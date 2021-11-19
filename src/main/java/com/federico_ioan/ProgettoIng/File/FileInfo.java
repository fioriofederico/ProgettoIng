package com.federico_ioan.ProgettoIng.File;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FileInfo {
	  private @Id @GeneratedValue Long id;
	  private @Column(nullable = false)String name;
	  private @Column(nullable = false, unique=true)String url;
	  private @Column(columnDefinition = "TIMESTAMP") LocalDateTime dateInsert;

	 /* public FileInfo(String name, String url) {
	    this.name = name;
	    this.url = url;
	  }*/
	  
	  public FileInfo() {}

	  public FileInfo(Long id, String name, String url, LocalDateTime dateInsert) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.dateInsert = dateInsert;
	}

	public String getName() {
	    return this.name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getUrl() {
	    return this.url;
	  }

	  public void setUrl(String url) {
	    this.url = url;
	  }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateInsert() {
		return dateInsert;
	}

	public void setDateInsert(LocalDateTime dateInsert) {
		this.dateInsert = dateInsert;
	}
	}
