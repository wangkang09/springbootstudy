package com.wangkang.mapper;

import com.wangkang.entity.Profiling;
import com.wangkang.entity.ProfilingExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ProfilingMapper {
    Profiling showProfile();

    int selectOtherDatabase();

    long countByExample(ProfilingExample example);

    int deleteByExample(ProfilingExample example);

    int insert(Profiling record);

    int insertSelective(Profiling record);

    List<Profiling> selectByExample(ProfilingExample example);

    int updateByExampleSelective(@Param("record") Profiling record, @Param("example") ProfilingExample example);

    int updateByExample(@Param("record") Profiling record, @Param("example") ProfilingExample example);
}