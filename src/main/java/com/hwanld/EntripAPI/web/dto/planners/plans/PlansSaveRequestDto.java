package com.hwanld.EntripAPI.web.dto.planners.plans;

import com.hwanld.EntripAPI.domain.planners.plans.Plans;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlansSaveRequestDto {
    private String date;
    private String todo;
    private String time;
    private String location;
    private Long rgb;
    private Long planner_id;

    @Builder
    public PlansSaveRequestDto (String date, String todo, int time, String location, Long rgb, Long planner_id) {
        this.date = date;
        this.todo = todo;
        this.time = Integer.toString(time);
        this.location = location;
        this.rgb = rgb;
        this.planner_id = planner_id;
    }

    public Plans toEntity() {

        return Plans.builder()
                .date(date)
                .todo(todo)
                .time(time)
                .location(location)
                .rgb(rgb)
                .build();
    }
}
