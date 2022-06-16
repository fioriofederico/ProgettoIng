package com.ProgettoIng.FedericoIoan.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role(ERole eRole) {
        this.name = eRole;
    }

    public static Role valueOf(String role) {
        return new Role(ERole.valueOf(role));
    }
}
