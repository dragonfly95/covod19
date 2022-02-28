package com.project.covid19.repository;

import com.project.covid19.entity.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Integer> {
    <T>List<T> findAllBy(Class<T> hashMapClass);
}
