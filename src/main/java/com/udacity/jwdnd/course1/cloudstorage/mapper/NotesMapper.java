package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {

    @Insert("INSERT INTO Notes (noteTitle, noteDescription, userId) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    Integer saveNote(Notes notes);

    @Select("SELECT * FROM Notes WHERE noteId = #{noteId}")
    Notes selectNoteById(Integer noteId);

    @Select("SELECT * FROM Notes WHERE noteTitle = #{noteTitle}")
    Notes selectNoteByTitle(String noteTitle);

    @Select("SELECT * FROM Notes WHERE userId = #{userId}")
    List<Notes> selectAllNotes(Integer userId);

    @Update("UPDATE Notes SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription}, userId = #{userId} WHERE noteId = #{noteId}")
    Integer updateNote(Notes notes);

    @Delete("DELETE FROM Notes WHERE noteId = #{noteId}")
    void deleteNote(Integer noteId);
}
