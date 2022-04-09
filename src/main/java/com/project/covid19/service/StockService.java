package com.project.covid19.service;


import com.project.covid19.entity.Stock;
import com.project.covid19.repository.StockRepository;
import com.project.covid19.vo.StockVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<StockVO> getStock() {
        List<Stock> list = stockRepository.findAll();
//        List<StockVO> collect = list.stream().map(StockVO::new).collect(Collectors.toList());

        List<StockVO> aa = new ArrayList<>();
        for (Stock stock : list) {
            StockVO stockVO = new StockVO();
            stockVO.setId(stock.getId());
            stockVO.setProductName(stock.getProductName());
            stockVO.setCount(stock.getCount());
            aa.add(stockVO);
        }
        return aa;
    }

    public void insertStock(StockVO vo) {
        Stock stock = vo.of();
        stockRepository.save(stock);
    }

    public void updateStock(StockVO vo) {
        Stock stock = vo.of();
        stockRepository.save(stock);
    }

    public void deleteStock(String id) {
        stockRepository.deleteById(id);
    }
}
