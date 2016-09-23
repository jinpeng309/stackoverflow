package com.capslock.stackoverflow.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by alvin.
 */
@Data
@AllArgsConstructor
@Builder
public class User {
    private int id;
    private int reputation = 0;
    private String displayName = "''";
    private long creationDate;
    private long lastAccessDate;
    private String websiteUrl = "''";
    private String location = "''";
    private String aboutMe = "''";
    private int views = 0;
    private int upVotes = 0;
    private int downVotes = 0;
    private int accountId = 0;
    private int age = -1;
}
