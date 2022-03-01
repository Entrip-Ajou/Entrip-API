package com.hwanld.EntripAPI.web.dto;

import com.hwanld.EntripAPI.domain.plans.Plans;
import lombok.Builder;

public class PlansSaveRequestDto {
    private String name;
    private String start_time;
    private String end_time;
    private String location;
    private String author;

    @Builder
    public PlansSaveRequestDto (String name, String start_time, String end_time, String location, String author) {
        this.name = name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.location = location;
        this.author = author;
    }

    public Plans toEntity() {
        return Plans.builder()
                .name(name)
                .start_time(start_time)
                .end_time(end_time)
                .location(location)
                .author(author)
                .build();
    }
}
