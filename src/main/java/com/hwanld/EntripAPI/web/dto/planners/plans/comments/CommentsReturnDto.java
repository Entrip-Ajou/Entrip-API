package com.hwanld.EntripAPI.web.dto.planners.plans.comments;

import lombok.Getter;

@Getter
public class CommentsReturnDto {

    private Long comment_id;
    private String author;
    private String content;
    private Long plan_id;

    public CommentsReturnDto (CommentsResponseDto responseDto) {
        this.comment_id = responseDto.getComment_id();
        this.author = responseDto.getAuthor();
        this.content = responseDto.getContent();
        this.plan_id = responseDto.getPlans().getPlan_id();
    }
}
