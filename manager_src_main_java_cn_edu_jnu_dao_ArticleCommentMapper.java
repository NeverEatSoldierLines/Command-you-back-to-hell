package cn.edu.jnu.dao;

import cn.edu.jnu.entity.ArticleComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleCommentMapper {

    List<String> selectCommentsByUserId(Integer userId);

    boolean deleteComment(String comment);
}