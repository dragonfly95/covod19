package com.project.covid19.repository;

import com.project.covid19.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, String> {
}
