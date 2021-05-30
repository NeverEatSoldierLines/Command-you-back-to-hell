package cn.edu.jnu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class LikeRecord implements Serializable {
    private Integer recordId;

    private Integer userId;

    private Integer articleId;

    private static final long serialVersionUID = 1L;
}