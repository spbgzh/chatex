package com.chatex.mapper;

import com.chatex.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface UserManageMapper {


    @Select("select role from user_role where id = #{id}")
    String getRoleByID(@Param("id") int id);


    @Insert("insert into user "
            + "(username, password, first_name, last_name, email, phone_number) "
            + "values "
            + "(#{username}, #{password}, #{first_name}, #{last_name}, #{email}, #{phone_number}) ")
    int insertUser(@Param("username") String username,
                   @Param("password") String password,
                   @Param("first_name") String first_name,
                   @Param("last_name") String last_name,
                   @Param("email") String email,
                   @Param("phone_number") String phone_number
    );

    @Insert("insert into user_role "
            + "(id,role) "
            + "values "
            + "(#{id}, #{role}) ")
    int insertUserRole(@Param("id") int id,
                       @Param("role") String role);


    @Select("select count(*) from user where username = #{username}")
    int judgeUsernameExist(@Param("username") String username);

    @Select("select id from user where username = #{username}")
    int getIDByUsername(@Param("username") String username);

    @Select("select password from user where username = #{username}")
    String getPasswordByUsername(@Param("username") String username);

    @Select("select first_name from user where username = #{username}")
    String getFirst_nameByUsername(@Param("username") String username);

    @Select("select last_name from user where username = #{username}")
    String getLast_nameByUsername(@Param("username") String username);

    @Select("select email from user where username = #{username}")
    String getEmailByUsername(@Param("username") String username);

    @Select("select phone_number from user where username = #{username}")
    String getPhone_numberByUsername(@Param("username") String username);

    @Update("update user set password = #{password} where username = #{username}")
    int updatePasswordByUsername(@Param("username") String username,
                                 @Param("password") String password);

    @Select("select * from user")
    List<User> queryUserList();


    @Select("select * from user where id = #{id}")
    User userByID(@Param("id") int id);


    @Insert("insert into user       "
            + "(id, name, pwd)    "
            + "values                   "
            + "(#{id}, #{name}, #{pwd}) ")
    int addUser(@Param("id") int id,
                @Param("name") String name,
                @Param("pwd") String pwd);


}
