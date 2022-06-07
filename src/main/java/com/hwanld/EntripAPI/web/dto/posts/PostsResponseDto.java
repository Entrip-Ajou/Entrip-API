package com.hwanld.EntripAPI.web.dto.posts;

import com.hwanld.EntripAPI.domain.posts.Posts;
import com.hwanld.EntripAPI.domain.users.Users;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long post_id;
    private String users;
    private String title;
    private String content;
    private String time_stamp;

    public PostsResponseDto (Posts entity) {
        this.post_id = entity.getPost_id();
        this.users = entity.getUsers().getUser_id();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.time_stamp = entity.getTimeStamp().toString();
    }
}
