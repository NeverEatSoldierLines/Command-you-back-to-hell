package cn.edu.jnu.service.impl;

import cn.edu.jnu.dao.*;
import cn.edu.jnu.service.DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteServiceImpl implements DeleteService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private ActionMapper actionMapper;
    @Override
    public boolean deleteUserByUserId(String userId) {
        boolean isSuccess = userMapper.deleteUserById(Integer.valueOf(userId));
        return isSuccess;
    }

    @Override
    public boolean deleteComment(String comment) {
        boolean isSuccess = articleCommentMapper.deleteComment(comment);
        return isSuccess;
    }

    @Override
    public boolean deleteArticle(Integer articleId) {
        boolean isSuccess = articleMapper.deleteArticle(articleId);
        return isSuccess;
    }

    @Override
    public boolean deleteFile(Integer fileId) {
        boolean isSuccess = fileMapper.deleteFile(fileId);
        return isSuccess;
    }

    @Override
    public boolean deleteAction(Integer userId) {
        boolean isSuccess = actionMapper.deleteAction(userId);
        return isSuccess;
    }
}
