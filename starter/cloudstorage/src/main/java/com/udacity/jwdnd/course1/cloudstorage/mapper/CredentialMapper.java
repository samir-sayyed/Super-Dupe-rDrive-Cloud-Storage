package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> getAllCredentials(Integer userId);
//
//    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES(#{url},#{username},#{key},#{password},#{userId})")
//    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
//    int addCredential(Credential credential);

//
//    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{userName}, key = #{key}, password = #{password} WHERE credentialid = #{credentialId}")
//    void updateCredential(Integer credentialId, String url, String userName, String password);

    @Insert("INSERT INTO CREDENTIALS (url,username,key,password,userid) VALUES(#{url},#{username},#{key},#{password},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int addCredential(Credential credential);


    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialid = #{credentialId}")
    void update(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    void deleteCredential(Integer credentialId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialsId}")
    Credential getCredential(Integer credentialsId);

}
