package com.hwanld.EntripAPI.web.dto.planners.plans;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlansUpdateRequestDto {

    private String date;
    private String todo;
    private String time;
    private String location;
    private Long rgb;

    @Builder
    public PlansUpdateRequestDto (String date, String todo, String time, String location, Long rgb) {
        this.date = date;
        this.todo = todo;
        this.time = time;
        this.location = location;
        this.rgb = rgb;
    }
}
