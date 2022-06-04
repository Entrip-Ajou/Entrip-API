package com.hwanld.EntripAPI.web.controller;

import com.hwanld.EntripAPI.domain.Messages;
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

@RequiredArgsConstructor
@RestController
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping("/api/v1/comments")
    public ResponseEntity<Messages> save(@RequestBody CommentsSaveRequestDto requestDto) {
        Long saved_comment_id = commentsService.save(requestDto);
        CommentsResponseDto responseDto = commentsService.findById(saved_comment_id);
        CommentsReturnDto returnDto = new CommentsReturnDto(responseDto);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Comments is saved well")
                .data(returnDto)
                .build();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @PutMapping("/api/v1/comments/{comment_id}")
    public ResponseEntity<Messages> update (@PathVariable Long comment_id, @RequestBody CommentsUpdateRequestDto requestDto) {
        Long updated_comment_id = commentsService.update(comment_id, requestDto);
        CommentsResponseDto responseDto = commentsService.findById(updated_comment_id);
        CommentsReturnDto returnDto = new CommentsReturnDto(responseDto);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Comments is updated well")
                .data(returnDto)
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
        Long deleted_comment_id = commentsService.delete(comment_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Delete comments with " + deleted_comment_id)
                .data(deleted_comment_id)
                .build();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }
}
