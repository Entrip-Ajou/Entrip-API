package com.hwanld.EntripAPI.web.dto.planners.plans;

import lombok.Getter;

@Getter
public class PlansReturnDto {

    private Long plan_id;
    private String date;
    private String todo;
    private int time;
    private String location;
    private Long rgb;
    private Long planner_id;
    private Boolean isExistComments;

    public PlansReturnDto (PlansResponseDto responseDto) {
        this.plan_id = responseDto.getPlan_id();
        this.date = responseDto.getDate();
        this.todo = responseDto.getTodo();
        this.time = Integer.parseInt(responseDto.getTime());
        this.location = responseDto.getLocation();
        this.rgb = responseDto.getRgb();
        this.planner_id = responseDto.getPlanners().getPlanner_id();
        this.isExistComments = responseDto.getIsExistComments();
    }
}
