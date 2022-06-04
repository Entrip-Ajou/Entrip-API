package com.hwanld.EntripAPI.web.dto.posts;

import com.hwanld.EntripAPI.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long post_id;
    private String author;
    private String title;
    private String content;
    private String time_stamp;

    public PostsResponseDto (Posts entity) {
        this.post_id = entity.getPost_id();
        this.author = entity.getAuthor();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.time_stamp = entity.getTimeStamp().toString();
    }
}
