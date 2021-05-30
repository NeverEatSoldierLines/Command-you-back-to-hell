package cn.edu.jnu.service;

import cn.edu.jnu.entity.*;

import java.util.List;

public interface SelectService {

    String selectAdminPasswordByName(String adminName);

    Integer selectAdminIdByAdminName(String adminName);

    List<OptRecord> selectRecordByAdminId(Integer adminId);

    List<TempRequest> selectNotAvailableUsers();

    List<TempRequest> selectNotPermitActions();

    String selectUsernameById(Integer userId);

    List<String> selectUserCommentsByUserId(Integer userId);

    List<Article> selectUserArticlesByUserId(Integer userId);

    List<File> selectUserFilesByUserId(Integer userId);

    List<User> selectAllUsers();

    Integer selectUserIdByFileId(Integer fileId);

    String selectFileNameByFileId(Integer FileId);

    Integer selectUserIsAvailableById(String userId);

    List<OptRecord> selectRecordByAdminIdAndTime(Integer adminId, String optTime);
}
