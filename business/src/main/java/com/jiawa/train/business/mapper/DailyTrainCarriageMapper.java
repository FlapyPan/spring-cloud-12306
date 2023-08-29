package com.jiawa.train.business.mapper;

import com.jiawa.train.business.domain.DailyTrainCarriage;
import com.jiawa.train.business.domain.DailyTrainCarriageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyTrainCarriageMapper {
    long countByExample(DailyTrainCarriageExample example);

    int deleteByExample(DailyTrainCarriageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DailyTrainCarriage record);

    int insertSelective(DailyTrainCarriage record);

    List<DailyTrainCarriage> selectByExample(DailyTrainCarriageExample example);

    DailyTrainCarriage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DailyTrainCarriage record, @Param("example") DailyTrainCarriageExample example);

    int updateByExample(@Param("record") DailyTrainCarriage record, @Param("example") DailyTrainCarriageExample example);

    int updateByPrimaryKeySelective(DailyTrainCarriage record);

    int updateByPrimaryKey(DailyTrainCarriage record);
}