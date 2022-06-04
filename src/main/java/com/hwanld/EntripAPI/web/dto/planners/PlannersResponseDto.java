package com.hwanld.EntripAPI.web.dto.planners;

import com.hwanld.EntripAPI.domain.planners.Planners;
import com.hwanld.EntripAPI.domain.planners.plans.Plans;
import com.hwanld.EntripAPI.domain.users.Users;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class PlannersResponseDto {

    private Long planner_id;
    private String title;
    private String start_date;
    private String end_date;
    private Set<Plans> plans = new HashSet<>();
    private Set<Users> users = new HashSet<>();
    private String time_stamp;
    private String comment_timeStamp;

    public PlannersResponseDto (Planners entity) {
        this.planner_id = entity.getPlanner_id();
        this.title = entity.getTitle();
        this.start_date = entity.getStart_date();
        this.end_date = entity.getEnd_date();
        this.plans = entity.getPlans();
        this.users = entity.getUsers();
        this.time_stamp = entity.getTimeStamp().toString();
        this.comment_timeStamp = entity.getComment_timeStamp().toString();
    }
}
