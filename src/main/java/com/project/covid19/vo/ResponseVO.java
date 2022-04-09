package com.project.covid19.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO {
    private String msg;
    private int count;

    public ResponseVO(String msg) {
        this.msg = msg;
    }

}
