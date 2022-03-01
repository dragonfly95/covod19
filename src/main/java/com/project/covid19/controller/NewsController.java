package com.project.covid19.controller;


import com.project.covid19.service.NewsService;
import com.project.covid19.vo.NewsVO;
import com.project.covid19.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //----
    @RequestMapping(value = "/news", method = RequestMethod.POST)
    public ResponseVO createNews(@RequestBody NewsVO newsVO) {
        newsService.createNews(newsVO);
        return new ResponseVO("OK", 200);
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.PUT)
    public ResponseVO updateNews(@RequestBody NewsVO newsVO) {
        newsService.updateNews(newsVO);
        return new ResponseVO("OK", 200);
    }

    @RequestMapping(value = "/news/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteNews(@PathVariable String id) {
        newsService.deleteNews(id);
        return new ResponseVO("OK", 200);
    }
}
