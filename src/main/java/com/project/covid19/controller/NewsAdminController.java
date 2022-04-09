package com.project.covid19.controller;

import com.project.covid19.vo.NewsVO;
import com.project.covid19.service.NewsService;
import com.project.covid19.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;

@Controller
public class NewsAdminController {


    @Autowired
    private NewsService service;

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String getNewsManage(Pageable pageable, Model model) {


        List<NewsVO> list = service.getAllNews(pageable);
        int total = service.countAllNews();

        model.addAttribute("list", list);
        model.addAttribute("totalCount", total);
        return "news/list";
    }


    @RequestMapping(value = "/news/form", method = RequestMethod.GET)
    public String formNews(Model model) {
        List<HashMap> category = service.getCategory();

        model.addAttribute("category", category);
        return "news/form";
    }


    @RequestMapping(value = "/news/form/{id}", method = RequestMethod.GET)
    public String formNews(@PathVariable("id") String id, Model model) {
        NewsVO vo = service.detailNews(id);
        List<HashMap> category = service.getCategory();

        model.addAttribute("vo", vo);
        model.addAttribute("category", category);
        return "news/form";
    }

    @RequestMapping(value = "/popup/news/view/{id}", method = RequestMethod.GET)
    public String viewNews(@PathVariable("id") String id, Model model) {
        NewsVO vo = service.detailNews(id);

        model.addAttribute("vo", vo);
        return "news/view";
    }



}
