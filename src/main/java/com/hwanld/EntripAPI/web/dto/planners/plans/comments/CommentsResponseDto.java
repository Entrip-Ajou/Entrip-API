package com.hwanld.EntripAPI.web.dto.planners.plans.comments;

import com.hwanld.EntripAPI.domain.planners.plans.Plans;
import com.hwanld.EntripAPI.domain.planners.plans.comments.Comments;
import lombok.Getter;

@Getter
public class CommentsResponseDto {

    private Long comment_id;
    private String author;
    private String content;
    private Plans plans;

    public CommentsResponseDto (Comments entity) {
        this.comment_id = entity.getComment_id();
        this.author = entity.getAuthor();
        this.content = entity.getContent();
        this.plans = entity.getPlans();
    }
}
