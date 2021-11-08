package com.chatex.mapper;

import com.chatex.pojo.UserIntroduction;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserIntroductionMapper {
    @Insert("insert into user_introduction "
            + "(id,somethingToSay) "
            + "values "
            + "(#{id}, #{somethingToSay}) ")
    int insertUserIntroduction(@Param("id") int id,
                               @Param("somethingToSay") String somethingToSay);

    @Select("select somethingToSay from user_introduction where id = #{id}")
    String getSomethingToSayByID(@Param("id") int id);

    @Select("select * from user_introduction where id = #{id}")
    UserIntroduction getUserIntroductionByID(@Param("id") int id);

    @Update("update user_introduction set somethingToSay = #{somethingToSay} where id = #{id}")
    int updateSomethingToSayByID(@Param("id") int id,
                                 @Param("somethingToSay") String somethingToSay);

    @Delete("delete from user_introduction where id = #{id}")
    int deleteUserIntroductionByID(@Param("id") int id);
}
