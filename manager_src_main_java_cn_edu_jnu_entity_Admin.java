package cn.edu.jnu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Admin implements Serializable {
    private Integer adminId;

    private String adminName;

    private String password;

    private static final long serialVersionUID = 1L;
}