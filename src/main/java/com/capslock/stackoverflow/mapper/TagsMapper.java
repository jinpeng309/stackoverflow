package com.capslock.stackoverflow.mapper;

import com.capslock.stackoverflow.model.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by alvin.
 */
@Mapper
public interface TagsMapper {
    @Insert({
            "<script>",
            "insert into tags (",
            "id,",
            "tagName,",
            "count,",
            "excerptPostId,",
            "wikiPostId",
            ")",
            "values ",
            "<foreach  collection='tagList' item='tag' separator=','>",
            "(",
            "#{tag.id, jdbcType=INTEGER}, ",
            "#{tag.tagName, jdbcType=VARCHAR}, ",
            "#{tag.count, jdbcType=INTEGER}, ",
            "#{tag.excerptPostId, jdbcType=BIGINT}, ",
            "#{tag.wikiPostId, jdbcType=BIGINT}",
            ")",
            "</foreach>",
            "</script>"
    })
    int insertBatch(@Param("tagList") final List<Tag> tagList);
}
