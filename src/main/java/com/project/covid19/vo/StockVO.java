package com.project.covid19.vo;

import com.project.covid19.entity.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockVO {

    private String id;

    private String productName;

    private int count;

    public StockVO(Stock vo) {
        this.id = vo.getId();
        this.productName = vo.getProductName();
        this.count = vo.getCount();
    }

    public Stock of() {
        Stock stock = new Stock();
        stock.setCount(this.getCount());
        stock.setProductName(this.getProductName());
        stock.setId(this.getId());
        return stock;
    }
}
