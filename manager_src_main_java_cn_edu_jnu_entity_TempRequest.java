package cn.edu.jnu.entity;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Data
@Component
public class TempRequest  implements Serializable {

    private  Integer userId;
    private String username;
    private String requestDetail;
    //因为无法区分请求类型，必须在这里加type区分
    private String requestType;
}
