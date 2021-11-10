package com.chatex.mapper;

import com.chatex.pojo.UserLikeDislike;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserLikeDislikeMapper {
    @Select("select * from user_like_dislike")
    List<UserLikeDislike> getUserLikeDislikeList();

    @Select("select * from user_like_dislike where id = #{id}")
    UserLikeDislike getUserLikeDislikeByID(@Param("id") int id);

    @Select("select userLike from user_like_dislike where id = #{id}")
    int getLikeByID(@Param("id") int id);

    @Select("select userDislike from user_like_dislike where id = #{id}")
    int getDislikeByID(@Param("id") int id);

    @Update("update user_like_dislike set userLike = #{userLike} where id = #{id}")
    void updateLikeByID(@Param("id") int id,
                       @Param("userLike") int userLike);


    @Update("update user_like_dislike set userDislike = #{userDislike} where id = #{id}")
    void updateDislikeByID(@Param("id") int id,
                       @Param("userDislike") int userDislike);

    @Insert("insert into user_like_dislike "
            + "(userLike, userDislike) "
            + "values "
            + "(#{userLike}, #{userDislike}) ")
    void insertUserLikeDislike(@Param("userLike") int userLike,
                   @Param("userDislike") int userDislike);

    @Delete("delete from user_like_dislike where id = #{id}")
    void deleteUserLikeDislike(@Param("id") int id);

}
