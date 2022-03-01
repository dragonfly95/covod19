package com.project.covid19.controller;


import com.project.covid19.service.StockService;
import com.project.covid19.vo.ResponseVO;
import com.project.covid19.vo.StockVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "/stock")
    public List<StockVO> getStock() {
        List<StockVO> list = stockService.getStock();
        return list;
    }

    @PostMapping(value = "/stock")
    public ResponseVO insertStock(@RequestBody StockVO vo) {
        stockService.insertStock(vo);
        return new ResponseVO("OK", 200);
    }

    @PutMapping(value = "/stock/{id}")
    public ResponseVO updateStock(@RequestBody StockVO vo) {
        stockService.updateStock(vo);
        return new ResponseVO("OK", 200);
    }

    @DeleteMapping(value = "/stock/{id}")
    public ResponseVO deleteStock(@PathVariable("id") String id) {
        stockService.deleteStock(id);
        return new ResponseVO("OK", 200);
    }

}
