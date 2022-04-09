package com.project.covid19.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping(value = "test")
    public String test() {
        return "smarteditor2";
    }
}
