package com.hwanld.EntripAPI.web;

import com.hwanld.EntripAPI.service.plans.PlansService;
import com.hwanld.EntripAPI.web.dto.PlansSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PlansApiController {

    private final PlansService plansService;

    @PostMapping("/api/v1/plans")
    public Long save(@RequestBody PlansSaveRequestDto requestDto) {
        return plansService.save(requestDto);
    }
}
