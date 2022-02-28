package com.project.covid19.repository;

import com.project.covid19.entity.News;
import com.project.covid19.vo.NewsVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsRepository extends JpaRepository<News, String> {
    <T>Page<T> findAllBy(Class<T> newsVOClass, Pageable pageable);

}
