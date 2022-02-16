package com.federico_ioan.ProgettoIng.Chat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue
    private Long id;


    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateInsert;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateUpdate;

}