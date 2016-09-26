package com.capslock.stackoverflow.mapper;

import com.capslock.stackoverflow.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by alvin.
 */
@Mapper
public interface UserMapper {
    @Insert({
            "<script>",
            "insert into user (",
            "id, ",
            "reputation, ",
            "creationDate, ",
            "displayName, ",
            "lastAccessDate, ",
            "websiteUrl, ",
            "location, ",
            "aboutMe, ",
            "views, ",
            "upVotes, ",
            "downVotes, ",
            "accountId, ",
            "age",
            ")",
            "values ",
            "<foreach  collection='userList' item='user' separator=','>",
            "(",
            "#{user.id, jdbcType=INTEGER}, ",
            "#{user.reputation, jdbcType=INTEGER}, ",
            "#{user.creationDate, jdbcType=BIGINT}, ",
            "#{user.displayName, jdbcType=VARCHAR}, ",
            "#{user.lastAccessDate, jdbcType=BIGINT}, ",
            "#{user.websiteUrl, jdbcType=VARCHAR}, ",
            "#{user.location, jdbcType=VARCHAR}, ",
            "#{user.aboutMe, jdbcType=VARCHAR}, ",
            "#{user.views, jdbcType=INTEGER}, ",
            "#{user.upVotes, jdbcType=INTEGER}, ",
            "#{user.downVotes, jdbcType=INTEGER}, ",
            "#{user.accountId, jdbcType=INTEGER}, ",
            "#{user.age, jdbcType=INTEGER}",
            ")",
            "</foreach>",
            "</script>"
    })
    int insertBatch(@Param("userList") final List<User> userList);
}
