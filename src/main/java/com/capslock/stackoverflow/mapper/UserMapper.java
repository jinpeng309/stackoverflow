package com.capslock.stackoverflow.mapper;

import com.capslock.stackoverflow.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by alvin.
 */

/**
 * id INTEGER PRIMARY KEY,
 * reputation INTEGER  NOT NULL DEFAULT 0,
 * creationDate BIGINT,
 * displayName VARCHAR(40) NOT NULL DEFAULT "",
 * lastAccessDate BIGINT,
 * websiteUrl VARCHAR(128) NOT NULL DEFAULT "",
 * location VARCHAR(128) NOT NULL DEFAULT "",
 * abounMe VARCHAR(1024) NOT NULL DEFAULT "",
 * views INTEGER NOT NULL DEFAULT 0,
 * upVotes INTEGER NOT NULL DEFAULT 0,
 * downVotes INTEGER NOT NULL DEFAULT 0,
 * accountId INTEGER NOT NULL DEFAULT -1,
 * age INTEGER NOT NULL DEFAULT  -1
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
