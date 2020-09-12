package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES")
    List<File> getAllFiles();

    @Select("SELECT * FROM FILES where filename = #{fileName}")
    File getFilebyFilename(String fileName);


    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys=true, keyProperty="fileId")
    int insertFile(File file);


    @Update("UPDATE FILES SET filename = #{newFileName} where notetitle = #{currentFileName}")
    void updateFile(String newFileName, String currentFileName);

    
    @Delete("DELETE FROM FILES WHERE filename = #{fileName}")
    void deleteFileByFilename(String fileName);


}
