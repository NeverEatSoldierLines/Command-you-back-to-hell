package cn.edu.jnu.dao;

import cn.edu.jnu.entity.Action;
import cn.edu.jnu.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActionMapper {


    List<Integer> selectNotPermittedActionUserId();

    List<Action> selectActionsByUserId(Integer userId);

    boolean permitAction(Integer userId);

    boolean deleteAction(Integer userId);
}