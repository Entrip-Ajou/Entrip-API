package com.hwanld.EntripAPI.web.dto.planners.plans;

import com.hwanld.EntripAPI.domain.planners.Planners;
import com.hwanld.EntripAPI.domain.planners.plans.Plans;
import com.hwanld.EntripAPI.domain.planners.plans.comments.Comments;
import lombok.Getter;

import java.util.Set;

@Getter
public class PlansResponseDto {

    private Long plan_id;
    private String date;
    private String todo;
    private String time;
    private String location;
    private Long rgb;
    private Planners planners;
    private Set<Comments> comments;
    private Boolean isExistComments;

    public PlansResponseDto (Plans entity) {
        this.plan_id = entity.getPlan_id();
        this.date = entity.getDate();
        this.todo = entity.getTodo();
        this.time = entity.getTime();
        this.location = entity.getLocation();
        this.rgb = entity.getRgb();
        this.planners = entity.getPlanners();
        this.comments = entity.getComments();
        this.isExistComments = !entity.isExistComments();
    }
}
