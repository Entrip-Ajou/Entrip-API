package com.hwanld.EntripAPI.web.controller;

import com.hwanld.EntripAPI.domain.Messages;
import com.hwanld.EntripAPI.service.fcm.FcmService;
import com.hwanld.EntripAPI.web.dto.fcm.FcmResponseDto;
import com.hwanld.EntripAPI.web.dto.fcm.FcmSaveRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.charset.Charset;

@RequiredArgsConstructor
@Controller
public class FcmController {

    private final FcmService fcmService;

    @PostMapping("/api/v1/test")
    public ResponseEntity<Messages> save (@RequestBody FcmSaveRequestDto requestDto) {
        String user_id = fcmService.save(requestDto);
        FcmResponseDto responseDto = fcmService.findById(user_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("FCM is saved well")
                .data(requestDto)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @GetMapping("/api/v1/test/{user_id}")
    public ResponseEntity<Messages> getToken (@PathVariable String user_id) {
        String token = fcmService.getToken(user_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Get token with id : " + user_id)
                .data(token)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }
}
