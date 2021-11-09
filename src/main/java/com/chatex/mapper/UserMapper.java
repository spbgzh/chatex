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

    @Update("update user set Role = #{Role} where username = #{username}")
    int updateRoleByUsername(@Param("username") String username,
                             @Param("Role") String Role);

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
            + "(age, username, password, gender, first_name, last_name, email, phone_number, role) "
            + "values "
            + "(#{age}, #{username}, #{password}, #{gender},#{first_name}, #{last_name}, #{email}, #{phone_number}, #{role}) ")
    int insertUser(@Param("age") String age,
                   @Param("username") String username,
                   @Param("password") String password,
                   @Param("gender") String gender,
                   @Param("first_name") String first_name,
                   @Param("last_name") String last_name,
                   @Param("email") String email,
                   @Param("phone_number") String phone_number,
                   @Param("role") String role
    );

    @Select("select count(*) from user where username = #{username}")
    int judgeUsernameExist(@Param("username") String username);

    @Select("select * from user where username = #{username}")
    User getUserByUsername(@Param("username") String username);

    @Select("select * from user where id = #{id}")
    User getUserByID(@Param("id") int id);


    @Select("select * from user where id != #{id} && age >= #{minAge} && age <= #{maxAge} && gender = #{genderRestriction}")
    List<User> getUserListWithoutIdGenderRestriction(@Param("id") int id,
                                                     @Param("minAge") String minAge,
                                                     @Param("maxAge") String maxAge,
                                                     @Param("genderRestriction") String genderRestriction
    );

    @Select("select * from user where id != #{id} && age >= #{minAge} && age <= #{maxAge}")
    List<User> getUserListWithoutId(@Param("id") int id,
                                    @Param("minAge") String minAge,
                                    @Param("maxAge") String maxAge);

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


    @Delete("delete from user where id = #{id}")
    int deleteUserByID(@Param("id") int id);

    @Select("select role from user where username = #{username}")
    String getRoleByUsername(@Param("username") String username);


}
