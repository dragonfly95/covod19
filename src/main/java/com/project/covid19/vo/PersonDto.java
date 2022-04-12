package com.project.covid19.vo;

import com.project.covid19.entity.Paddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private Long id;

    private String name;
    private List<PaddressDto> address;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaddressDto {
        private Long id;
        private String zipcode;
        private String street;
    }
}

