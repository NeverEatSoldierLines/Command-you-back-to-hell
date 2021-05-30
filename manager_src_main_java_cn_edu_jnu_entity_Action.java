package cn.edu.jnu.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Action implements Serializable {
    private Integer actionId;

    private String actionName;

    private Date actionTime;

    private String actionLocation;

    private String actionDetail;

    private Integer userId;

    private String permitted;
}