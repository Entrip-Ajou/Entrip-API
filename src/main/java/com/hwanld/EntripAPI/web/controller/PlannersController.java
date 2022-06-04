package com.hwanld.EntripAPI.web.controller;

import com.hwanld.EntripAPI.domain.Messages;
import com.hwanld.EntripAPI.service.planners.PlannersService;
import com.hwanld.EntripAPI.web.dto.planners.PlannersResponseDto;
import com.hwanld.EntripAPI.web.dto.planners.PlannersReturnDto;
import com.hwanld.EntripAPI.web.dto.planners.PlannersSaveRequestDto;
import com.hwanld.EntripAPI.web.dto.planners.PlannersUpdateRequestDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.PlansReturnDto;
import com.hwanld.EntripAPI.web.dto.users.UsersReturnDto;
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
public class PlannersController {

    private final PlannersService plannersService;

    @PostMapping("/api/v1/planners")
    public ResponseEntity<Messages> save(@RequestBody PlannersSaveRequestDto requestDto) {
        Long saved_planner_id = plannersService.save(requestDto);
        PlannersResponseDto responseDto = plannersService.findByPlannerId(saved_planner_id);
        PlannersReturnDto returnDto = new PlannersReturnDto(responseDto);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Planner is saved well")
                .data(returnDto)
                .build();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @PutMapping("/api/v1/planners/{planner_id}")
    public ResponseEntity<Messages> update (@PathVariable Long planner_id, @RequestBody PlannersUpdateRequestDto requestDto) {
        Long updated_planner_id = plannersService.update(planner_id, requestDto);
        PlannersResponseDto responseDto = plannersService.findByPlannerId(planner_id);
        PlannersReturnDto returnDto = new PlannersReturnDto(responseDto);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Planner is updated well")
                .data(returnDto)
                .build();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @GetMapping("/api/v1/planners/{planner_id}")
    public ResponseEntity<Messages> findById(@PathVariable Long planner_id) {
        PlannersResponseDto responseDto = plannersService.findByPlannerId(planner_id);
        PlannersReturnDto returnDto = new PlannersReturnDto(responseDto);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Load planner with " + planner_id)
                .data(returnDto)
                .build();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

//    @GetMapping("api/v1/planners/{planner_id}/{date}")
//    public ResponseEntity<Messages> findByPlannerIdWithDate(@PathVariable Long planner_id, @PathVariable String date) {
//        List<PlansReturnDto> plansList = plannersService.findByPlannerIdWithDate(planner_id, date);
//        Messages messages = Messages.builder()
//                .httpStatus(200)
//                .message("Load planner with specific date " + date)
//                .data(plansList)
//                .build();
//
//        HttpHeaders headers= new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
//    }

    @GetMapping("api/v1/planners/{planner_id}/all")
    public ResponseEntity<Messages> findAllPlansWithPlannerId(@PathVariable Long planner_id) {
        List<PlansReturnDto> plansList = plannersService.findAllPlansWithPlannerId(planner_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Load all plans with specific planner id " + planner_id)
                .data(plansList)
                .build();

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @GetMapping("api/v1/planners/{planner_id}/exist")
    public ResponseEntity<Messages> plannerIsExistWithId(@PathVariable Long planner_id) {
        Boolean isExist = plannersService.plannerIsExistWithId(planner_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Find if planners is exist with specific planner id " + planner_id)
                .data(isExist)
                .build();

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);

    }

    @DeleteMapping("/api/v1/planners/{planner_id}")
    public ResponseEntity<Messages> delete(@PathVariable Long planner_id) {
        Long deleted_planner_id = plannersService.delete(planner_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Delete planner with " + planner_id)
                .data(deleted_planner_id)
                .build();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @PutMapping("api/v1/planners/{planner_id}/{user_id}")
    public ResponseEntity<Messages> addFriendToPlanner(@PathVariable Long planner_id, @PathVariable String user_id) {
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message(plannersService.addFriendToPlanner(planner_id, user_id))
                .data(planner_id)
                .build();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @GetMapping("api/v1/planners/{planner_id}/getAllUser")
    public ResponseEntity<Messages> findAllUsersWithPlannerId(@PathVariable Long planner_id) {
        List<UsersReturnDto> usersList = plannersService.findAllUsersWithPlannerId(planner_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Load all users with planner id : " + planner_id)
                .data(usersList)
                .build();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @GetMapping("api/v1/planners/{planner_id}/{user_id}/exist")
    public ResponseEntity<Messages> userIsExistWithPlanner (@PathVariable Long planner_id, @PathVariable String user_id) {
        Boolean trueOrFalse = plannersService.userIsExistWithPlanner(planner_id,user_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Check If User (" + user_id + ") is exist in planner (" + planner_id + ")")
                .data(trueOrFalse)
                .build();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }
}
