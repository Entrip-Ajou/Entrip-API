package com.hwanld.EntripAPI.web.dto.posts;

import com.hwanld.EntripAPI.domain.posts.Posts;
import com.hwanld.EntripAPI.domain.users.Users;
import com.hwanld.EntripAPI.domain.users.UsersRepository;
import com.hwanld.EntripAPI.service.users.UsersService;
import com.hwanld.EntripAPI.web.dto.users.UsersResponseDto;
import com.hwanld.EntripAPI.web.dto.users.UsersSaveRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String user_id;
    private String title;
    private String content;

    @Builder
    public PostsSaveRequestDto (String user_id, String title, String content) {
        this.user_id = user_id;
        this.title = title;
        this.content = content;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .build();
    }
}
