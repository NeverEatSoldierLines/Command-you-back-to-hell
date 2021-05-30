package cn.edu.jnu.service;

public interface UpdateService {

    boolean banUser(Integer userId);

    void recordOpt(String opt, Integer adminId);

    boolean releaseUser(Integer valueOf);

    boolean permitAction(Integer userId);

    boolean limitUser(Integer userId);

    boolean updateUserInfo(Integer userId);

}
