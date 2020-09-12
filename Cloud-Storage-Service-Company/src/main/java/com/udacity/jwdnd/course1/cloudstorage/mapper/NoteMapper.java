package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES")
    List<Note> getAllNotes();

    @Select("SELECT * FROM NOTES where notetitle = #{noteTitle}")
    Note getNoteByTitle(String noteTitle);


    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys=true, keyProperty="noteId")
    int insertNote(Note note);


    @Update("UPDATE NOTES SET notetitle = #{newNoteTitle} where notetitle = #{currentNoteTitle}")
    void updateNoteTitleByTitle(String newNoteTitle, String currentNoteTitle);

    @Update("UPDATE NOTES SET notedescription = #{noteDescription} where notetitle = #{noteTitle}")
    void updateNoteDescByTitle(String noteDescription, String noteTitle);


    @Delete("DELETE FROM NOTES WHERE notetitle = #{noteTitle}")
    void deleteNoteByTitle(String noteTitle);

}
