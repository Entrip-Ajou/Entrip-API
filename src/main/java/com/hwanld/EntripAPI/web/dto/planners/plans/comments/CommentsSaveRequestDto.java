package com.hwanld.EntripAPI.web.dto.planners.plans.comments;

import com.hwanld.EntripAPI.domain.planners.plans.comments.Comments;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsSaveRequestDto {
    private String author;
    private String content;
    private Long plans_id;

    @Builder
    public CommentsSaveRequestDto (String author, String content, Long plans_id) {
        this.author = author;
        this.content = content;
        this.plans_id = plans_id;
    }

    public Comments toEntity() {
        return Comments.builder()
                .author(author)
                .content(content)
                .build();
    }
}
