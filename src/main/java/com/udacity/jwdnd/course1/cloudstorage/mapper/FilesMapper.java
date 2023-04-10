package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {

    @Insert("INSERT INTO Files (fileName, contentType, fileSize, userId, fileData) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer saveFile(Files files);

    @Select("SELECT * FROM Files WHERE fileId = #{fileId}")
    Files selectFileById(Integer fileId);

    @Select("SELECT * FROM Files WHERE fileName = #{fileName}")
    Files getFileByName(String fileName);

    @Select("SELECT * FROM Files WHERE userId = #{userId}")
    List<Files> selectAllFiles(Integer userId);

    @Delete("DELETE FROM Files WHERE fileId = #{fileId}")
    void deleteFile(Integer fileId);
}
