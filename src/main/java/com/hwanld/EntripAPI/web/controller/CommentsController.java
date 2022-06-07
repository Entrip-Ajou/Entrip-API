package com.hwanld.EntripAPI.web.controller;

import com.hwanld.EntripAPI.domain.Messages;
import com.hwanld.EntripAPI.domain.planners.plans.comments.Comments;
import com.hwanld.EntripAPI.service.planners.plans.comments.CommentsService;
import com.hwanld.EntripAPI.web.dto.planners.plans.comments.CommentsResponseDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.comments.CommentsReturnDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.comments.CommentsSaveRequestDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.comments.CommentsUpdateRequestDto;
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
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping("/api/v1/comments")
    public ResponseEntity<Messages> save(@RequestBody CommentsSaveRequestDto requestDto) {
        List<CommentsReturnDto> returnDtoList = commentsService.save(requestDto);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Comments is saved well")
                .data(returnDtoList)
                .build();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @PutMapping("/api/v1/comments/{comment_id}")
    public ResponseEntity<Messages> update (@PathVariable Long comment_id, @RequestBody CommentsUpdateRequestDto requestDto) {
        List<CommentsReturnDto> returnDtoList = commentsService.update(comment_id,requestDto);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Comments is updated well")
                .data(returnDtoList)
                .build();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @GetMapping("/api/v1/comments/{comment_id}")
    public ResponseEntity<Messages> findById(@PathVariable Long comment_id) {
        CommentsResponseDto responseDto = commentsService.findById(comment_id);
        CommentsReturnDto returnDto = new CommentsReturnDto(responseDto);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Load comments with " + responseDto.getComment_id())
                .data(returnDto)
                .build();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @DeleteMapping("/api/v1/comments/{comment_id}")
    public ResponseEntity<Messages> delete (@PathVariable Long comment_id) {
        List<CommentsReturnDto> returnDtoList = commentsService.delete(comment_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Delete comments with " + comment_id)
                .data(returnDtoList)
                .build();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @GetMapping("/api/v1/comments/{plan_id}/getAllComments")
    public ResponseEntity<Messages> getAllCommentsWithPlanId (@PathVariable Long plan_id) {
        List<CommentsReturnDto> returnDtoList = commentsService.getAllCommentsWithPlanId(plan_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Get all comments with plan id :  " + plan_id)
                .data(returnDtoList)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }
}
