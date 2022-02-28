package com.project.covid19.service;

import com.project.covid19.repository.NewsCategoryRepository;
import com.project.covid19.repository.NewsRepository;
import com.project.covid19.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsCategoryRepository newsCategoryRepository;

    public int updateNews(NewsVO newsVO) {
        return 0;
    }

    public int createNews(NewsVO newsVO) {
        return 0;
    }

    public NewsVO detailNews(String id) {
        return null;
    }

    public List<HashMap> getCategory() {
        return new ArrayList<>();
    }

    public int countAllNews() {
        return 0;
    }

    public List<NewsVO> getAllNews(Pageable pageable) {
        return new ArrayList<>();
    }
}
