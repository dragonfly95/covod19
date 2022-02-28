package com.project.covid19.repository;

import com.project.covid19.entity.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory, Integer> {
}
