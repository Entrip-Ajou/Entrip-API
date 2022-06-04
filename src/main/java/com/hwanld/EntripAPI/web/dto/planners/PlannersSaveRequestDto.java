package com.hwanld.EntripAPI.web.dto.planners;

import com.hwanld.EntripAPI.domain.planners.Planners;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class PlannersSaveRequestDto {

    private String user_id;

    @Builder
    public PlannersSaveRequestDto (String user_id) {
        this.user_id = user_id;
    }

    public Planners toEntity() {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return Planners.builder()
                .title("제목 없음")
                .start_date(time)
                .end_date(time)
                .build();
    }

}
