package com.project.covid19.repository;

import com.project.covid19.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

// crud
// create , retreive , update, delete
public interface StockRepository extends JpaRepository<Stock, String> {
}
