package com.project.covid19.controller;


import com.project.covid19.service.NewsService;
import com.project.covid19.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/news")
    public Page<NewsVO> getNews(Pageable pageable) {
        Page<NewsVO> list = newsService.getNews(pageable);
        return list;
    }


    @RequestMapping(value = "/news/{id}", method = RequestMethod.GET)
    public NewsVO getNewsDetail(@PathVariable("id") String id, Model model) {
        NewsVO vo = newsService.detailNews(id);
        return vo;
    }
}
