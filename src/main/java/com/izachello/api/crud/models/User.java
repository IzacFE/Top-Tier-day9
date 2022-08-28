package com.izachello.api.crud.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_user_id_seq")
    @SequenceGenerator(name = "generator_user_id_seq",sequenceName = "user_id_seq", schema = "public", allocationSize = 1)
    private long userId;

    @Column(name = "name")
    private String name;
}
