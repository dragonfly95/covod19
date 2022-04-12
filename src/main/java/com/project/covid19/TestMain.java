package com.project.covid19;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestMain {

    public static void main(String[] args) {

        List<Person> people = Arrays.asList(
                new Person(1, "name1", "addres1", 1234),
                new Person(1, "name1", "addres2", 1235),
                new Person(2, "beast", "addres3", 12333333),
                new Person(2, "beast", "addres4", 12344444));

        ModelMapper modelMapper = new ModelMapper();


        TestMain test = new TestMain();
//        List<PersonDto> arr = test.splitObject(modelMapper, people);
//        System.out.println("arr = " + arr);

        test.groupObject(modelMapper, people);
        System.out.println("arr = ");

    }

    public void groupObject(ModelMapper modelMapper, List<Person> people) {
        Map<String, List<Person>> collect = people
                .stream()
                .collect(Collectors.groupingBy(Person::getName));

        System.out.println("collect = " + collect);
        for (String name : collect.keySet()) {
            List<Person> people1 = collect.get(name);
        }

        PersonDto[] data = modelMapper.map(people, PersonDto[].class);
        System.out.println("data = " + data);
    }


    public List<PersonDto> splitObject(ModelMapper modelMapper, List<Person> people) {

        Map<PersonDto, List<Person>> collect1 = people
                .stream()
                .collect(Collectors.groupingBy((p) -> p.convert(p)));
        System.out.println("collect1 = " + collect1);

        ArrayList<PersonDto> arr = new ArrayList<>();
        for (PersonDto personDto : collect1.keySet()) {
            System.out.println("personDto = " + personDto);
            List<Person> people1 = collect1.get(personDto);
            List<Address> addresses = Arrays.asList(modelMapper.map(people1, Address[].class));
            personDto.setAddress(addresses);
            arr.add(personDto);
        }
        return arr;
    }
}
