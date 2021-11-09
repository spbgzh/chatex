package com.chatex.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface UserRoleMapper {
    @Select("select role from user_role where id = #{id}")
    String getRoleByID(@Param("id") int id);

    @Insert("insert into user_role "
            + "(id,role) "
            + "values "
            + "(#{id}, #{role}) ")
    int insertUserRole(@Param("id") int id,
                       @Param("role") String role);

    @Delete("delete from user_role where id = #{id}")
    int deleteUserRoleByID(@Param("id") int id);
}
