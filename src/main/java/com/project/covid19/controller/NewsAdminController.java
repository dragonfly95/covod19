package com.project.covid19.controller;

import com.project.covid19.vo.NewsVO;
import com.project.covid19.service.NewsService;
import com.project.covid19.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;

public class NewsAdminController {


    @Autowired
    private NewsService service;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getNewsManage(Pageable pageable, Model model) {


        List<NewsVO> list = service.getAllNews(pageable);
        int total = service.countAllNews();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", total);
        return "daum/list";
    }


    @RequestMapping(value = "/news/form", method = RequestMethod.GET)
    public String formNews(Model model) {
        List<HashMap> category = service.getCategory();

        model.addAttribute("category", category);
        return "daum/form";
    }


    @RequestMapping(value = "/news/form/{id}", method = RequestMethod.GET)
    public String formNews(@PathVariable("id") String id, Model model) {
        NewsVO vo = service.detailNews(id);
        List<HashMap> category = service.getCategory();

        model.addAttribute("vo", vo);
        model.addAttribute("category", category);
        return "daum/form";
    }

    @RequestMapping(value = "/popup/news/view/{id}", method = RequestMethod.GET)
    public String viewNews(@PathVariable("id") String id, Model model) {
        NewsVO vo = service.detailNews(id);

        model.addAttribute("vo", vo);
        return "daum/view";
    }

    //----
    @RequestMapping(value = "/news/create", method = RequestMethod.POST)
    public ResponseVO createNews(@RequestBody NewsVO newsVO) {
        int cnt = service.createNews(newsVO);
        return new ResponseVO("OK", 200);
    }

    @RequestMapping(value = "/news/update", method = RequestMethod.PUT)
    public ResponseVO updateNews(@RequestBody NewsVO newsVO) {
        int cnt = service.updateNews(newsVO);
        return new ResponseVO("OK", 200);
    }

}
