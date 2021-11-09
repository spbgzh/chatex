package com.chatex.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HobbyScoreMapper {
    @Select("select * from hobby_scores where id = #{id}")
    List<Integer> getListValueByID(@Param("id") int id);


}
