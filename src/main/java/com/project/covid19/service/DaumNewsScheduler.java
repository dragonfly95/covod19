package com.project.covid19.service;

import com.project.covid19.entity.News;
import com.project.covid19.repository.NewsRepository;
import com.project.covid19.vo.NewsVO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class DaumNewsScheduler {


    @Autowired
    private NewsRepository mapper;

    // 10분마다 실행
    @Scheduled(cron = "0 */1 * * * *")
    public void scrawal() throws Exception {
        String url = "https://news.daum.net/";
        Connection conn = Jsoup.connect(url);
        Document document = conn.get();

        final Elements news_list = document.select(".item_issue");
        final Elements thumbnailElem = document.select(".item_issue > a > img");
        final Elements linkElem = document.select(".item_issue > a");
        final Elements titleElem = document.select(".item_issue .tit_g > .link_txt");
        final Elements companyElem = document.select(".logo_cp > .thumb_g");
        final Elements categoryElem = document.select(".txt_category");

        final int size = news_list.size();
        List<News> data = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            try {
                String thumbnail = getElementSource(thumbnailElem, i);
                String link = getElementLink(linkElem, i);
                String title = getElementText(titleElem, i);
                String newspaper = getElementAlt(companyElem, i);
                String category  = getElementText(categoryElem, i);
                String id = link.split("/")[4];

                Connection conn1 = Jsoup.connect(link);
                Document document1 = conn1.get();


                Elements contentsElem = document1.select("#harmonyContainer");
                Elements reportElem = document1.select("span.txt_info");
                Elements thumbnailEleme = document1.select("figure.origin_fig img");

                String title_contents = getElementText(contentsElem, 0);
                String reporter = getElementText(reportElem, 0);
                String titleThumbnail = getElementSource(thumbnailEleme, 0);

                News news = new News();
                news.setId(id);
                news.setThumbnail(thumbnail);
                news.setSummary("summary");
                news.setCategory(category);
                news.setTitle_name(title);
                news.setTitle_thumbnail(titleThumbnail);
                news.setTitle_contents(title_contents);
                news.setReporter(reporter);
                news.setOpen_yn("y");
                news.setView_count(0);
                news.setNewspaper(newspaper);
                news.setLink(link);
                news.setReg_date(new Date());
                data.add(news);
                System.out.println("====================== = ");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("finished = ");

        mapper.saveAll(data);
    }

    private static String getElementAlt(Elements elements, int index) {
        return elements.get(index).attr("alt");
    }

    private static String getElementText(Elements elements, int index) {
        return elements.get(index).text();
    }

    private static String getElementSource(Elements elements, int index) {
        return elements.get(index).attr("src");
    }
    private static String getElementLink(Elements elements, int index) {
        return elements.get(index).attr("href");
    }

}
