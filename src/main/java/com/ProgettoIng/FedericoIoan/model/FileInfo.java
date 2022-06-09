package com.ProgettoIng.FedericoIoan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
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

    public FileInfo(String fileName, String fileUrl) {
        this.name = fileName;
        this.url = fileUrl;
    }
}
