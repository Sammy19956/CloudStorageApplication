package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {

    @Insert("INSERT INTO Credentials (url, userName, key, password, userId) VALUES (#{url}, #{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    Integer saveCredential(Credentials credentials);

    @Select("SELECT * FROM Credentials WHERE credentialId = #{credentialId}")
    Credentials selectCredentialById(Integer credentialId);

    @Select("SELECT * FROM Credentials WHERE credentialId = #{credentialId}")
    List<Credentials> selectCredentialByIds(Integer credentialId);

    @Select("SELECT * FROM Credentials WHERE url = #{url}")
    Credentials selectCredentialByUrl(String url);

    @Select("SELECT * FROM Credentials WHERE userId = #{userId}")
    List<Credentials> getAllCredentials(Integer userId);

    @Update("UPDATE Credentials SET url = #{url}, userName = #{userName}, key = #{key}, password = #{password}, userId = #{userId} WHERE credentialId = #{credentialId}")
    Integer updateCredential(Credentials credentials);

    @Delete("DELETE FROM Credentials WHERE credentialId = #{credentialId}")
    void deleteCredential(Integer credentialId);
}
