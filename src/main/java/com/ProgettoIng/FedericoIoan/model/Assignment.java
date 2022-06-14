package com.ProgettoIng.FedericoIoan.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "assignments", uniqueConstraints = {
        @UniqueConstraint(columnNames = "code")
})
public class Assignment {

    @Id
    @GeneratedValue
    private Long id;

    private String code;

    private String name;

    private String type;

    private Long size;

    private int score;

    @ManyToOne
    @JoinColumn(name = "delivery_folder_id")
    @JsonIgnore
    private DeliveryFolder deliveryFolder;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private User student;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateInsert;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateUpdate;
}
