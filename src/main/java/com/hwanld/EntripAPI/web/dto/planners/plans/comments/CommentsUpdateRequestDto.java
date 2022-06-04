package com.hwanld.EntripAPI.web.dto.planners.plans.comments;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsUpdateRequestDto {

    private String author;
    private String content;
    private Long plans_id;

    @Builder
    public CommentsUpdateRequestDto (String author, String content, Long plans_id) {
        this.author = author;
        this.content = content;
        this.plans_id = plans_id;
    }
}
