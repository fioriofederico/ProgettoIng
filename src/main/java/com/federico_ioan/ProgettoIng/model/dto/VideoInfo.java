package com.federico_ioan.ProgettoIng.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class VideoInfo {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition="TEXT")
    private String description;

    @Column(nullable = false, unique = true)
    private String url;

    @Column(nullable = false, unique = true)
    private String endPointVimeo;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateInsert;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateUpdate;

    public VideoInfo() {
    }

    public VideoInfo(String name, String url, String endPointVimeo, String description) {
        super();
        this.name = name;
        this.url = url;
        this.endPointVimeo = endPointVimeo;
        this.description = description;
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

    public String getEndPointVimeo() {
        return endPointVimeo;
    }

    public void setEndPointVimeo(String endPointVimeo) {
        this.endPointVimeo = endPointVimeo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateInsert() {
        return dateInsert;
    }

    public LocalDateTime getDateUpdate() {
        return dateUpdate;
    }
}
