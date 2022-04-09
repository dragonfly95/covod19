package com.project.covid19;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private int id;
    private String name;
    private List<Address> address;

}
