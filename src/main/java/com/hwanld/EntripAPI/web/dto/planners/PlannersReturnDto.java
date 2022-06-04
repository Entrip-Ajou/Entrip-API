package com.hwanld.EntripAPI.web.dto.planners;

import lombok.Getter;

@Getter
public class PlannersReturnDto {

    private Long planner_id;
    private String title;
    private String start_date;
    private String end_date;
    private String time_stamp;
    private String comment_timeStamp;

    public PlannersReturnDto (PlannersResponseDto responseDto) {
        this.planner_id = responseDto.getPlanner_id();
        this.title = responseDto.getTitle();
        this.start_date = responseDto.getStart_date();
        this.end_date = responseDto.getEnd_date();
        this.time_stamp = responseDto.getTime_stamp();
        this.comment_timeStamp = responseDto.getComment_timeStamp();
    }
}
