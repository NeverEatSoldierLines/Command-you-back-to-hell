package cn.edu.jnu.service.impl;

import cn.edu.jnu.dao.*;
import cn.edu.jnu.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.TimerTask;

@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OptRecordMapper optRecordMapper;

    @Autowired
    private ActionMapper actionMapper;
    @Override
    public boolean banUser(Integer userId) {
        boolean isSuccess = userMapper.banUser(userId);
        return isSuccess;
    }

    @Override
    public void recordOpt(String opt, Integer adminId) {
        optRecordMapper.addOpt(opt,adminId);
    }

    @Override
    public boolean releaseUser(Integer userId) {
        boolean isSuccess = userMapper.releaseUser(userId);
        return isSuccess;
    }

    @Override
    public boolean permitAction(Integer userId) {
        boolean isSuccess = actionMapper.permitAction(userId);
        return isSuccess;
    }

    @Override
    public boolean limitUser(Integer userId) {
        boolean isSuccess = userMapper.limitUser(userId);
        return isSuccess;
    }

    @Override
    public boolean updateUserInfo(Integer userId) {
        boolean isSuccess = userMapper.updateUserInfo(userId);
        return isSuccess;
    }
}
