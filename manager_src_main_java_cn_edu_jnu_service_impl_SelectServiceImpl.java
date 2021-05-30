package cn.edu.jnu.service.impl;

import cn.edu.jnu.dao.*;
import cn.edu.jnu.entity.*;
import cn.edu.jnu.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SelectServiceImpl implements SelectService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private OptRecordMapper optRecordMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TempRequest request;

    @Autowired
    private ActionMapper actionMapper;

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private FileMapper fileMapper;
    @Override
    public String selectAdminPasswordByName(String adminName) {
        return adminMapper.selectAdminPasswordByName(adminName);
    }

    @Override
    public Integer selectAdminIdByAdminName(String adminName) {
        return adminMapper.selectAdminIdByAdminName(adminName);
    }

    @Override
    public List<OptRecord> selectRecordByAdminId(Integer adminId) {
        return optRecordMapper.selectOptRecordById(adminId);
    }

    @Override
    public List<TempRequest> selectNotAvailableUsers() {
        //注销请求列表
        List<TempRequest> requests1 = new ArrayList<>();
        //找出isavailable为0的userid
        List<Integer> userIds1 = userMapper.selectUsersNotAvailable();
        //拼接成tempRequest
        for (Integer userId:userIds1){
            String username = userMapper.selectUsernameById(userId);
            request.setUserId(userId);
            request.setUsername(username);
            request.setRequestDetail("用户"+username+"请求注销");
            request.setRequestType("注销账号");
            requests1.add(request);
        }
        return requests1;
    }

    @Override
    public List<TempRequest> selectNotPermitActions() {

        //宣传活动请求列表
        List<TempRequest> requests2 = new ArrayList<>();
        //找出permited为no的活动宣传请求的用户
        List<Integer> userIds2 = actionMapper.selectNotPermittedActionUserId();
        //拼接成tempRequest
        //
        for (Integer userId:userIds2){
            //活动请求申请复杂一点，一个用户不止一条请求
            List<Action> actionList = actionMapper.selectActionsByUserId(userId);
            for (Action action:actionList){
                String username = userMapper.selectUsernameById(userId);
                //这里的 request是用户请求的含义
                request.setUserId(userId);
                request.setUsername(username);
                request.setRequestDetail("用户"+username+"请求宣传活动："+action.getActionName()+",活动详情："
                        +action.getActionDetail()+",活动地点："+action.getActionLocation()+"，活动时间:"+ action.getActionTime().toLocaleString());
                request.setRequestType("活动宣传");
                requests2.add(request);
            }
        }
        //返回请求列表2（活动宣传）
        return requests2;
    }

    @Override
    public String selectUsernameById(Integer userId) {
        return userMapper.selectUsernameById(userId);
    }

    @Override
    public List<String> selectUserCommentsByUserId(Integer userId) {
        return articleCommentMapper.selectCommentsByUserId(userId);
    }

    @Override
    public List<Article> selectUserArticlesByUserId(Integer userId) {
        return articleMapper.selectArticlesByUserId(userId);
    }

    @Override
    public List<File> selectUserFilesByUserId(Integer userId) {
        return fileMapper.selectFilesByUserId(userId);
    }

    @Override
    public List<User> selectAllUsers() {
        return userMapper.selectAllUsers();
    }

    @Override
    public Integer selectUserIdByFileId(Integer fileId) {
        return fileMapper.selectUserIdByFileId(fileId);
    }

    @Override
    public String selectFileNameByFileId(Integer fileId) {
        return fileMapper.selectFileNameByFileId(fileId);
    }

    @Override
    public Integer selectUserIsAvailableById(String userId) {
        return userMapper.selectUserIsAvailableById(Integer.parseInt(userId));
    }

    @Override
    public List<OptRecord> selectRecordByAdminIdAndTime(Integer adminId, String optTime) {
        List<OptRecord> optRecordList = optRecordMapper.selectOptRecordsByIdAndTime(adminId,optTime);
        return optRecordList;
    }
}
