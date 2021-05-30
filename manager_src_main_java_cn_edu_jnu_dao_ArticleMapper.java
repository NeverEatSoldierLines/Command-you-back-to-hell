package cn.edu.jnu.dao;

import cn.edu.jnu.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    List<Article> selectArticlesByUserId(Integer userId);

    boolean deleteArticle(Integer articleId);
}