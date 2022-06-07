package com.hwanld.EntripAPI.web.controller;

import com.hwanld.EntripAPI.domain.Messages;
import com.hwanld.EntripAPI.service.planners.plans.PlansService;
import com.hwanld.EntripAPI.web.dto.planners.plans.PlansResponseDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.PlansReturnDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.PlansSaveRequestDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.PlansUpdateRequestDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.comments.CommentsReturnDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PlansController {

    private final PlansService plansService;

    @PostMapping("/api/v1/plans")
    public ResponseEntity<Messages> save(@RequestBody PlansSaveRequestDto requestDto) {
        Long saved_plan_id = plansService.save(requestDto);
        PlansResponseDto responseDto = plansService.findById(saved_plan_id);
        PlansReturnDto returnDto = new PlansReturnDto(responseDto);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Plans is saved well")
                .data(returnDto)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @PutMapping("/api/v1/plans/{plan_id}")
    public ResponseEntity<Messages> update(@PathVariable Long plan_id, @RequestBody PlansUpdateRequestDto requestDto) {
        Long updated_planner_id = plan_id;
        plansService.update(plan_id, requestDto);
        PlansResponseDto responseDto = plansService.findById(plan_id);
        PlansReturnDto returnDto = new PlansReturnDto(responseDto);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Plans is saved well")
                .data(returnDto)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @GetMapping("/api/v1/plans/{plan_id}")
    public ResponseEntity<Messages> findById(@PathVariable Long plan_id) {
        PlansResponseDto plansResponseDto = plansService.findById(plan_id);
        PlansReturnDto returnDto = new PlansReturnDto(plansResponseDto);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Load plan with " + plan_id)
                .data(returnDto)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/plans/{plan_id}")
    public ResponseEntity<Messages> delete(@PathVariable Long plan_id) {
        Long deleted_plan_id = plansService.delete(plan_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Delete plan with " + plan_id)
                .data(deleted_plan_id)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

}
