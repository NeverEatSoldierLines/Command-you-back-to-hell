package cn.edu.jnu.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.PropertyResourceBundle;

@Data
public class File implements Serializable {
    private Integer fileId;

    private String fileName;

    private Integer fileSize;

    private Integer downloadNumber;

    private Integer userId;

    private String tag1;

    private String tag2;

    private static final long serialVersionUID = 1L;
}