package cn.edu.jnu.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    String selectAdminPasswordByName(String adminName);

    Integer selectAdminIdByAdminName(String adminName);
}