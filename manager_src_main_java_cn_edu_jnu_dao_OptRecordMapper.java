package cn.edu.jnu.dao;

import cn.edu.jnu.entity.OptRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OptRecordMapper {

    List<OptRecord> selectOptRecordById(Integer adminId);

    void addOpt(String opt, Integer adminId);

    List<OptRecord> selectOptRecordsByIdAndTime(Integer adminId, String optTime);
}