package cn.edu.jnu.entity;


import lombok.Data;
import org.attoparser.dom.Text;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Article implements Serializable {
    private Integer articleId;

    private Integer commentNumber;

    private String articleName;

    private Integer userId;

    private Date uploadTime;

    private String content;

    private String userName;

    private String tag1;

    private String tag2;

    private Integer readNumber;

    private Integer likes;
}