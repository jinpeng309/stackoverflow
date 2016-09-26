package com.capslock.stackoverflow.model;

import lombok.Data;

/**
 * Created by alvin.
 */
@Data
public class Tag {
    private int id;
    private String tagName;
    private int count;
    private long excerptPostId;
    private long wikiPostId;
}
