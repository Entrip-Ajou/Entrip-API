package com.hwanld.EntripAPI.web.dto.planners;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlannersUpdateRequestDto {

    private String title;
    private String start_date;
    private String end_date;

    @Builder
    public PlannersUpdateRequestDto (String title, String start_date, String end_date) {
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}
