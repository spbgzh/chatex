package com.chatex.mapper;

import com.chatex.pojo.User;
import com.chatex.pojo.UserIntroduction;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {



    // user
    @Update("update user set password = #{password} where username = #{username}")
    int updatePasswordByUsername(@Param("username") String username,
                                 @Param("password") String password);

    @Update("update user set age = #{age}," +
            " username = #{username}, gender = #{gender}, first_name = #{first_name}," +
            " last_name = #{last_name}, email = #{email}, phone_number = #{phone_number}" +
            " where id = #{id}")
    int updateUserByID(@Param("id") int id,
                   @Param("age") String age,
                   @Param("username") String username,
                   @Param("gender") String gender,
                   @Param("first_name") String first_name,
                   @Param("last_name") String last_name,
                   @Param("email") String email,
                   @Param("phone_number") String phone_number);

    @Insert("insert into user "
            + "(age, username, password, gender, first_name, last_name, email, phone_number) "
            + "values "
            + "(#{age}, #{username}, #{password}, #{gender},#{first_name}, #{last_name}, #{email}, #{phone_number}) ")
    int insertUser(@Param("age") String age,
                   @Param("username") String username,
                   @Param("password") String password,
                   @Param("gender") String gender,
                   @Param("first_name") String first_name,
                   @Param("last_name") String last_name,
                   @Param("email") String email,
                   @Param("phone_number") String phone_number
    );

    @Select("select count(*) from user where username = #{username}")
    int judgeUsernameExist(@Param("username") String username);

    @Select("select * from user where username = #{username}")
    User getUserByUsername(@Param("username") String username);

    @Select("select id from user where username = #{username}")
    int getIDByUsername(@Param("username") String username);

    @Select("select age from user where username = #{username}")
    String getAgeByUsername(@Param("age") String username);

    @Select("select gender from user where username = #{username}")
    String getGenderByUsername(@Param("gender") String username);

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


    @Select("select * from user")
    List<User> getUserList();


    @Select("select * from user where id = #{id}")
    User userByID(@Param("id") int id);


    @Insert("insert into user       "
            + "(id, name, pwd)    "
            + "values                   "
            + "(#{id}, #{name}, #{pwd}) ")
    int addUser(@Param("id") int id,
                @Param("name") String name,
                @Param("pwd") String pwd);


    @Delete("delete from user where id = #{id}")
    int deleteUserByID(@Param("id") int id);






}
