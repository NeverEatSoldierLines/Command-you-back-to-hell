package cn.edu.jnu.service;


public interface DeleteService {

    boolean deleteUserByUserId(String userId);

    boolean deleteComment(String comment);

    boolean deleteArticle(Integer articleId);

    boolean deleteFile(Integer fileId);

    boolean deleteAction(Integer userId);
}
