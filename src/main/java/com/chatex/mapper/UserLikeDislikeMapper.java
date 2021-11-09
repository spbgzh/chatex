package com.chatex.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserLikeDislikeMapper {
    @Update("update user_like_dislike set userLike = #{userLike} where id = #{id}")
    int updateLikeByID(@Param("id") int id,
                       @Param("userLike") int userLike);


    @Update("update user_like_dislike set userDislike = #{userDislike} where id = #{id}")
    int updateDislikeByID(@Param("id") int id,
                       @Param("userDislike") int userDislike);

    @Insert("insert into user_like_dislike "
            + "(userLike, userDislike) "
            + "values "
            + "(#{userLike}, #{userDislike}) ")
    int insertUserLikeDislike(@Param("userLike") int userLike,
                   @Param("userDislike") int userDislike);

}
