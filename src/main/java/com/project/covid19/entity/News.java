package com.project.covid19.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
public class News {

    @Id
    private String id;

    private Date reg_date;
    private String category;
    private String thumbnail;
    private String summary;
    private String title_thumbnail;
    private String title_name;

    @Column(columnDefinition = "TEXT")
    private String title_contents;
    private String reporter;
    private String newspaper;
    private String open_yn;
    private int view_count;
    private String link;

}
