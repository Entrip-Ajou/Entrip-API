package com.hwanld.EntripAPI.web.controller;

import com.hwanld.EntripAPI.domain.Messages;
import com.hwanld.EntripAPI.domain.exceptions.NicknameOrUserIdNotValidException;
import com.hwanld.EntripAPI.service.users.UsersService;
import com.hwanld.EntripAPI.web.dto.planners.PlannersReturnDto;
import com.hwanld.EntripAPI.web.dto.users.UsersResponseDto;
import com.hwanld.EntripAPI.web.dto.users.UsersReturnDto;
import com.hwanld.EntripAPI.web.dto.users.UsersSaveRequestDto;
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
public class UsersController {
    private final UsersService usersService;

    @PostMapping("/api/v1/users")
    public ResponseEntity<Messages> save(@RequestBody UsersSaveRequestDto requestDto) {
        String user_id = usersService.save(requestDto);
        UsersResponseDto responseDto = usersService.findByUserId(user_id);
        UsersReturnDto returnDto = new UsersReturnDto(responseDto);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Users is saved well")
                .data(returnDto)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @PutMapping("/api/v1/users/{planner_id}/{user_id}")
    public ResponseEntity<Messages> addPlanners(@PathVariable Long planner_id, @PathVariable String user_id) {
        usersService.addPlanners(planner_id, user_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Add planner, id : " + planner_id + ", with User, id : " + user_id)
                .data(null)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @GetMapping("/api/v1/users/{user_id}")
    public ResponseEntity<Messages> findById(@PathVariable String user_id) {
        UsersResponseDto responseDto = usersService.findByUserId(user_id);
        UsersReturnDto returnDto = new UsersReturnDto(responseDto);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Load user with id : " + user_id)
                .data(returnDto)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @GetMapping("api/v1/users/{user_id}/all")
    public ResponseEntity<Messages> findAllPlannersWithUsersId(@PathVariable String user_id) {
        UsersResponseDto responseDto = usersService.findByUserId(user_id);
        List<PlannersReturnDto> plannersList = usersService.findAllPlannersWithUsersId(user_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Load all planners with id : " + user_id)
                .data(plannersList)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @GetMapping("api/v1/users/{nickname}/nickname/exist")
    public ResponseEntity<Messages> isExistNickName (@PathVariable String nickname) {
        Boolean isExist = usersService.isExistNickName(nickname);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Check if nickname : " + nickname + " is exist")
                .data(isExist)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @GetMapping("api/v1/users/{user_id}/user_id/exist")
    public ResponseEntity<Messages> isExistUserId (@PathVariable String user_id) {
        Boolean isExist = usersService.isExistUserId(user_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Check if user_id : " + user_id + " is exist")
                .data(isExist)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/users/{user_id}")
    public ResponseEntity<Messages> delete(@PathVariable String user_id) {
        String deleted_user_id = usersService.delete(user_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Delete user with id : " + user_id)
                .data(deleted_user_id)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @PutMapping("api/v1/users/token/{user_id}/{token}")
    public ResponseEntity<Messages> addToken(@PathVariable String user_id, @PathVariable String token) {
        String updated_user_id = usersService.updateToken(user_id, token);
        UsersResponseDto responseDto = usersService.findByUserId(user_id);
        UsersReturnDto returnDto = new UsersReturnDto(responseDto);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Update user " + user_id + "'s token : " + token)
                .data(returnDto)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @GetMapping("api/v1/users/findUserWithNicknameOrUserId/{nicknameOrUserId}")
    public ResponseEntity<Messages> findUserWithNicknameOrUserId (@PathVariable String nicknameOrUserId) throws NicknameOrUserIdNotValidException {
        try{
            String target_user_id = usersService.findUserWithNicknameOrUserId(nicknameOrUserId);
            UsersResponseDto responseDto = usersService.findByUserId(target_user_id);
            UsersReturnDto returnDto = new UsersReturnDto(responseDto);
            Messages messages = Messages.builder()
                    .httpStatus(200)
                    .message("Get user with nicknameOrUserId : " + nicknameOrUserId)
                    .data(returnDto)
                    .build();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return new ResponseEntity<>(messages, headers, HttpStatus.OK);
        } catch (NicknameOrUserIdNotValidException e) {
            throw new NicknameOrUserIdNotValidException();
        }
    }

    @PutMapping("api/v1/users/travelFavorite/{user_id}/{travelFavorite}")
    public ResponseEntity<Messages> updateTravelFavorite (@PathVariable String user_id, @PathVariable String travelFavorite) {
        String changed_travel_Favorite = usersService.updateTravelFavorite(user_id, travelFavorite);
        UsersResponseDto responseDto = usersService.findByUserId(user_id);
        UsersReturnDto returnDto = new UsersReturnDto(responseDto);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Set user " + user_id + "'s travelFavorite : " + travelFavorite)
                .data(returnDto)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

//    @PutMapping("api/v1/users/{user_id}/{friend_id}/add")
//    public ResponseEntity<Messages> addFriend(@PathVariable String user_id, @PathVariable String friend_id) {
//        usersService.addFriend(user_id, friend_id);
//        Messages messages = Messages.builder()
//                .httpStatus(200)
//                .message("Save friend : " + user_id + " , " + friend_id)
//                .data(null)
//                .build();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
//    }
//
//    @DeleteMapping("api/v1/users/{user_id}/{friend_id}/delete")
//    public ResponseEntity<Messages>deleteFriend(@PathVariable String user_id, @PathVariable String friend_id) {
//        usersService.deleteFriend(user_id, friend_id);
//        Messages messages = Messages.builder()
//                .httpStatus(200)
//                .message("Save friend : " + user_id + " , " + friend_id)
//                .data(null)
//                .build();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
//    }
}
