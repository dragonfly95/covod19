package com.project.covid19.vo;

import com.project.covid19.entity.News;
import com.project.covid19.service.NewsService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
public class NewsVO {

    private String id;
    private Date reg_date;
    private String category;
    private String thumbnail;
    private String summary;
    private String title_thumbnail;
    private String title_name;
    private String title_contents;
    private String reporter;
    private String newspaper;
    private String open_yn;
    private int view_count;
    private String link;

    public String getReg_date() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar c1 = Calendar.getInstance();
        return sdf.format(c1.getTime());
    }

    public NewsVO () {}

    public NewsVO (News news) {
        this.id = news.getId();
        this.reg_date = news.getReg_date();
        this.category = news.getCategory();
        this.thumbnail = news.getThumbnail();
        this.summary = news.getSummary();
        this.title_thumbnail = news.getTitle_thumbnail();
        this.title_name = news.getTitle_name();
        this.title_contents = news.getTitle_contents();
        this.reporter = news.getReporter();
        this.newspaper = news.getNewspaper();
        this.open_yn = news.getOpen_yn();
        this.view_count = news.getView_count();
        this.link = news.getLink();
    }
}
