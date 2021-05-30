package cn.edu.jnu.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OptRecord implements Serializable {
    private Integer optId;

    private Integer adminId;

    private Date optTime;

    private String optTranslation;

    private static final long serialVersionUID = 1L;

}