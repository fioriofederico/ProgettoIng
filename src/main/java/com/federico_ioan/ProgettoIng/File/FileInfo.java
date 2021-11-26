package com.federico_ioan.ProgettoIng.File;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FileInfo {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String url;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateInsert;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateUpdate;

    public FileInfo() {
    }

    public FileInfo(String name, String url) {
        super();
        this.name = name;
        this.url = url;
    }

	public Long getId() {
		return id;
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

    public LocalDateTime getDateInsert() {
        return dateInsert;
    }

	public LocalDateTime getDateUpdate() {
		return dateUpdate;
	}
}
