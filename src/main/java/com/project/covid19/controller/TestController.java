package com.project.covid19.controller;

import com.project.covid19.entity.Board;
import com.project.covid19.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    BoardService service;

    @GetMapping(value = "test")
    public String test(Model model) {

        Board board = service.readByBoardNo(1L);
        model.addAttribute("data", board);

        return "smarteditor2";
    }

    @PostMapping(value = "test/posting")
    public String posting(Model model, @RequestParam Map<String, String> param) {

        Board board = new Board();
        board.setTitle("sdf");
        board.setContents(param.get("ir1"));
        board.setWriter("wirter");
        board.setRegDate(new Date());

        service.writeBoard(board);

        model.addAttribute("data", board);

        return "smarteditor2";
    }
}
