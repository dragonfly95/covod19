package com.project.covid19.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Paddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String zipcode;
    private String street;


    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
