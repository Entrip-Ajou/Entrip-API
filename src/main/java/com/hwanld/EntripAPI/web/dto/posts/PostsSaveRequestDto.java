package com.hwanld.EntripAPI.web.dto.posts;

import com.hwanld.EntripAPI.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String author;
    private String title;
    private String content;

    @Builder
    public PostsSaveRequestDto (String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public Posts toEntity() {
        return Posts.builder()
                .author(author)
                .title(title)
                .content(content)
                .build();
    }
}
