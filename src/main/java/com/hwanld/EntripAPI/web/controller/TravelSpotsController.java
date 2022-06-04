package com.hwanld.EntripAPI.web.controller;

import com.hwanld.EntripAPI.domain.Messages;
import com.hwanld.EntripAPI.domain.travelSpots.TravelSpots;
import com.hwanld.EntripAPI.service.travelSpots.TravelSpotsService;
import com.hwanld.EntripAPI.web.dto.posts.PostsResponseDto;
import com.hwanld.EntripAPI.web.dto.posts.PostsSaveRequestDto;
import com.hwanld.EntripAPI.web.dto.travelSpots.TravelSpotsResponseDto;
import com.hwanld.EntripAPI.web.dto.travelSpots.TravelSpotsSaveRequestDto;
import com.hwanld.EntripAPI.web.dto.travelSpots.TravelSpotsUpdateRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RequiredArgsConstructor
@RestController
public class TravelSpotsController {

    private final TravelSpotsService travelSpotsService;

    @PostMapping("/api/v1/travelSpots")
    public ResponseEntity<Messages> save(@RequestBody TravelSpotsSaveRequestDto requestDto) {
        String saved_travelSpot_name = travelSpotsService.save(requestDto);
        TravelSpotsResponseDto responseDto = travelSpotsService.findById(saved_travelSpot_name);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("TravelSpots is saved well")
                .data(responseDto)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @PutMapping("api/v1/travelSpots/{travelSpot_name}")
    public ResponseEntity<Messages> update (@PathVariable String travelSpot_name, @RequestBody TravelSpotsUpdateRequestDto requestDto) {
        String updated_travelSpot_name = travelSpotsService.update(travelSpot_name, requestDto);
        TravelSpotsResponseDto responseDto = travelSpotsService.findById(travelSpot_name);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("TravelSpots is updated well")
                .data(responseDto)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @GetMapping("api/v1/travelSpots/{travelSpot_name}")
    public ResponseEntity<Messages> findById (@PathVariable String travelSpot_name) {
        TravelSpotsResponseDto responseDto = travelSpotsService.findById(travelSpot_name);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Load TravelSpots with name : " + travelSpot_name)
                .data(responseDto)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @DeleteMapping("api/v1/travelSpots/{travelSpot_name}")
    public ResponseEntity<Messages> delete (@PathVariable String travelSpot_name) {
        String deleted_travelSpot_name = travelSpotsService.delete(travelSpot_name);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("TravelSpots is deleted well")
                .data(deleted_travelSpot_name)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }
}
