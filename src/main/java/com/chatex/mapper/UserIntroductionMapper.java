package com.chatex.mapper;

import com.chatex.pojo.UserIntroduction;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserIntroductionMapper {
    @Insert("insert into user_introduction "
            + "(id, aboutMe,somethingToSay) "
            + "values "
            + "(#{id}, #{aboutMe}, #{somethingToSay}) ")
    void insertUserIntroduction(@Param("id") int id,
                               @Param("aboutMe") String aboutMe,
                               @Param("somethingToSay") String somethingToSay);

    @Select("select somethingToSay from user_introduction where id = #{id}")
    String getSomethingToSayByID(@Param("id") int id);

    @Select("select * from user_introduction where id = #{id}")
    UserIntroduction getUserIntroductionByID(@Param("id") int id);

    @Update("update user_introduction set somethingToSay = #{somethingToSay}, aboutMe = #{aboutMe} where id = #{id}")
    void updateSomethingToSayByID(@Param("id") int id,
                                 @Param("aboutMe") String aboutMe,
                                 @Param("somethingToSay") String somethingToSay);

    @Delete("delete from user_introduction where id = #{id}")
    void deleteUserIntroductionByID(@Param("id") int id);
}
