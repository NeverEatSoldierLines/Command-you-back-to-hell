package cn.edu.jnu.entity;

import cn.hutool.core.date.DateTime;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ArticleComment implements Serializable {
    private Integer commentId;

    private Integer articleId;

    private Integer userId;

    private String commentContent;

    private Date commentTime;

    private String username;
}