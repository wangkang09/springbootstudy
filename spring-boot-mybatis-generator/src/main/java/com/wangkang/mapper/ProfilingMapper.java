package com.wangkang.mapper;

import com.wangkang.entity.Profiling;
import com.wangkang.entity.ProfilingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProfilingMapper {
    long countByExample(ProfilingExample example);

    int deleteByExample(ProfilingExample example);

    int insert(Profiling record);

    int insertSelective(Profiling record);

    List<Profiling> selectByExample(ProfilingExample example);

    int updateByExampleSelective(@Param("record") Profiling record, @Param("example") ProfilingExample example);

    int updateByExample(@Param("record") Profiling record, @Param("example") ProfilingExample example);
}