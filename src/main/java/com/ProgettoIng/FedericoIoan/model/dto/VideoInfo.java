package com.ProgettoIng.FedericoIoan.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public VideoInfo(String originalFilename, String linkVideo, String videoEndPoint, String description) {
        this.name = originalFilename;
        this.url = linkVideo;
        this.endPointVimeo = videoEndPoint;
        this.description = description;
    }
}
