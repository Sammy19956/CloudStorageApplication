package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UsersMapper {
    @Insert("INSERT INTO Users (userName, salt, password, firstName, lastName) VALUES (#{userName}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Integer insertUser(Users users);

    @Select("SELECT * FROM Users WHERE userName = #{userName}")
    Users selectUser(String userName);

    @Update("UPDATE Users SET userName = #{useName}, salt = #{salt}, password = #{password}, firstName = #{firstName}, lastName = #{lastName} WHERE userId = #{userId}")
    void updateUser(Users users);

    @Delete("DELETE FROM Users WHERE userId = #{userId}")
    void deleteUser(Integer userId);

}
