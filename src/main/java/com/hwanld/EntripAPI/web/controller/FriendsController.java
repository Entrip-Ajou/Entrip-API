package com.hwanld.EntripAPI.web.controller;

import com.hwanld.EntripAPI.domain.Messages;
import com.hwanld.EntripAPI.service.users.friends.FriendsService;
import com.hwanld.EntripAPI.web.dto.users.friends.FriendsResponseDto;
import com.hwanld.EntripAPI.web.dto.users.friends.FriendsReturnDto;
import com.hwanld.EntripAPI.web.dto.users.friends.FriendsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RequiredArgsConstructor
@RestController
public class FriendsController {

    private final FriendsService friendsService;

    @PostMapping("/api/v1/friends")
    public ResponseEntity<Messages> save (@RequestBody FriendsSaveRequestDto requestDto) {
        Long saved_friends_id = friendsService.save(requestDto);
        FriendsResponseDto responseDto = friendsService.findById(saved_friends_id);
        FriendsReturnDto returnDto = new FriendsReturnDto(responseDto);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Friends is saved well")
                .data(returnDto)
                .build();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }
}
