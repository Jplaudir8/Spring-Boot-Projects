package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES where userid = #{userId}")
    List<Note> getAllUserNotes(int userId);


    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys=true, keyProperty="noteId")
    int insertNote(Note note);


    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} where noteid = #{noteId}")
    void updateNote(Note note);


    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    void deleteNoteById(int notId);

}
