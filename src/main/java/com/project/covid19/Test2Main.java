package com.project.covid19;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.commons.collections4.bag.TreeBag;
import org.modelmapper.ModelMapper;

import java.util.*;

public class Test2Main {

    public static void main(String[] args) {

        // Parse text to separate words
        String INPUT_TEXT = "Hello World! Hello All! Hi World!";
        // Create Multiset
        Bag bag = SynchronizedSortedBag.synchronizedBag(new TreeBag(Arrays.asList(INPUT_TEXT.split(" "))));

        // Print count words
        System.out.println(bag); // print [1:All!,2:Hello,1:Hi,2:World!]- in natural (alphabet) order
        // Print all unique words
        System.out.println(bag.uniqueSet());    // print [All!, Hello, Hi, World!]- in natural (alphabet) order


        // Print count occurrences of words
        System.out.println("Hello = " + bag.getCount("Hello"));    // print 2
        System.out.println("World = " + bag.getCount("World!"));    // print 2
        System.out.println("All = " + bag.getCount("All!"));    // print 1
        System.out.println("Hi = " + bag.getCount("Hi"));    // print 1
        System.out.println("Empty = " + bag.getCount("Empty"));    // print 0

        // Print count all words
        System.out.println(bag.size());    //print 6

        // Print count unique words
        System.out.println(bag.uniqueSet().size());    //print 4


        Customer customer1 = new Customer(1, "Daniel", "locality1", "city1");
        Customer customer2 = new Customer(2, "Fredrik", "locality2", "city2");
        Customer customer3 = new Customer(1, "Daniel", "locality7", "city7");

        List<Customer> arr = Arrays.asList(customer1, customer2, customer3);


        ModelMapper modelMapper = new ModelMapper();
        List<CustomerDto> customerDtos = Arrays.asList(modelMapper.map(arr, CustomerDto[].class));

        List<CustomerDto> result = new ArrayList<>();

        Bag bag1 = SynchronizedSortedBag
                .synchronizedBag(new HashBag<>(customerDtos));
        Set set = bag1.uniqueSet();
        for (Object o : set) {
            CustomerDto dto = (CustomerDto) o;
            List<Customer> select = ListUtils.select(arr, (customer) -> {
                Integer id = customer.getId();
                return id == dto.getId();
            });

            Collection<Address1> addr = CollectionUtils.collect(select, (customer) -> customer.getAddress());

            List<Address1> address1 = Arrays.asList(modelMapper.map(select, Address1[].class));
            dto.setAddress(new ArrayList<>(addr));

            result.add(dto);
        }

        System.out.println("result = " + result);





    }
}

@Value
class Customer {
    private Integer id;
    private String name;
    private Address1 address;

    public Customer(int id, String daniel, String locality1, String city1) {
        this.id = id;
        this.name = daniel;
        this.address = new Address1(locality1, city1);
    }

    // standard getters and setters
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Address1 {
    private String locality;
    private String city;

    // standard getters and setters
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class CustomerDto {
    private Integer id;
    private String name;
    private List<Address1> address;
}