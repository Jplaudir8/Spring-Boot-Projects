package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS")
    List<Credential> getAllCredentials();

    @Select("SELECT * FROM CREDENTIALS where credentialid = #{credentialId}")
    Note getCredentialById(int credentialId);


    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userId) VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys=true, keyProperty="credentialId")
    int insertCredential(Credential credential);


    @Update("UPDATE CREDENTIALS SET url = #{newUrl} where url = #{currentUrl}")
    void updateCredentialByUrl(String newUrl, String currentUrl);

    @Update("UPDATE CREDENTIALS SET username = #{newUsername} where username = #{currentUsername}")
    void updateCredentialByUsername(String newUsername, String currentUsername);

    @Update("UPDATE CREDENTIALS SET password = #{newPassword} where password = #{currentPassword}")
    void updateCredentialByPassword(String newPassword, String currentPassword);


    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void deleteCredentialById(int credentialId);

}
