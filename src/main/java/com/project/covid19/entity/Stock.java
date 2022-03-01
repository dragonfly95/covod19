package com.project.covid19.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Stock {

    @Id
    private String id;

    private String productName;

    private int count;

}
