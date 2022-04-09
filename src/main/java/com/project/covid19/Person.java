package com.project.covid19;

import lombok.Value;

@Value
public class Person {
    private int id;
    private String name;
    private String address;
    private int zip;

    public PersonDto convert(Person p) {
        return new PersonDto(p.getId(), p.getName(), null);
    }
}
