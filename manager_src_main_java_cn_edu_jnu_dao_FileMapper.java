package cn.edu.jnu.dao;

import cn.edu.jnu.entity.File;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    List<File> selectFilesByUserId(Integer userId);

    Integer selectUserIdByFileId(Integer fileId);

    String selectFileNameByFileId(Integer fileId);

    boolean deleteFile(Integer fileId);
}