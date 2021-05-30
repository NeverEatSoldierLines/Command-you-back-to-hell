package cn.edu.jnu.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String school;
    private String sex;
    private String major;
    private String selfDescription;
    private String birthday;
    private Short isAvailable;
    private String phoneNumber;
    private String profilePicture;
    private String realName;
    private String userLocation;
}