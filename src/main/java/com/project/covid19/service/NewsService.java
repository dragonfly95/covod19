package com.project.covid19.service;

import com.project.covid19.entity.News;
import com.project.covid19.repository.NewsCategoryRepository;
import com.project.covid19.repository.NewsRepository;
import com.project.covid19.vo.NewsVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NewsCategoryRepository newsCategoryRepository;

    public News updateNews(NewsVO newsVO) {
        News news = modelMapper.map(newsVO, News.class);
        return newsRepository.save(news);
    }

    public News createNews(NewsVO newsVO) {
        News news = modelMapper.map(newsVO, News.class);
        return newsRepository.save(news);
    }

    public NewsVO detailNews(String id) {
        return newsRepository.findById(id).map(NewsVO::new).orElse(new NewsVO());
    }

    public List<HashMap> getCategory() {
        return newsCategoryRepository.findAllBy(HashMap.class);
    }

    public int countAllNews() {
        return 0;
    }

    public List<NewsVO> getAllNews(Pageable pageable) {
        return new ArrayList<>();
    }

    public Page<NewsVO> getNews(Pageable pageable) {
        Page<News> data = newsRepository.findAllBy(News.class, pageable);
        List<NewsVO> contents = data.getContent().stream().map(NewsVO::new).collect(Collectors.toList());

        Page page = new PageImpl<NewsVO>(contents, pageable, data.getTotalElements());
        return page;
    }
}
