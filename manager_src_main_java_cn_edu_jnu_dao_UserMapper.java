package cn.edu.jnu.dao;

import cn.edu.jnu.entity.File;
import cn.edu.jnu.entity.TempRequest;
import cn.edu.jnu.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    List<Integer> selectUsersNotAvailable();

    String selectUsernameById(Integer userId);

    List<User> selectAllUsers();

    boolean banUser(Integer userId);

    boolean releaseUser(Integer userId);

    boolean deleteUserById(Integer userId);

    Integer selectUserIsAvailableById(Integer userId);

    boolean limitUser(Integer userId);

    boolean updateUserInfo(Integer userId);
}