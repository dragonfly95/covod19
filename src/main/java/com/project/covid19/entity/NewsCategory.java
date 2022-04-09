package com.project.covid19.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class NewsCategory {

    @Id
    private Integer id;
    private String name;
}
