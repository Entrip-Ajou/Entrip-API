package com.hwanld.EntripAPI.web.controller;

import com.hwanld.EntripAPI.domain.Messages;
import com.hwanld.EntripAPI.service.posts.PostsService;
import com.hwanld.EntripAPI.web.dto.posts.PostsResponseDto;
import com.hwanld.EntripAPI.web.dto.posts.PostsSaveRequestDto;
import com.hwanld.EntripAPI.web.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public ResponseEntity<Messages> save(@RequestBody PostsSaveRequestDto requestDto) {
        Long saved_post_id = postsService.save(requestDto);
        PostsResponseDto responseDto = postsService.findById(saved_post_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Posts is saved well")
                .data(responseDto)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @PutMapping("/api/v1/posts/{post_id}")
    public ResponseEntity<Messages> update(@PathVariable Long post_id, @RequestBody PostsUpdateRequestDto requestDto) {
        postsService.update(post_id, requestDto);
        PostsResponseDto responseDto = postsService.findById(post_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Posts is updated well")
                .data(responseDto)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @GetMapping("/api/v1/posts/{post_id}")
    public ResponseEntity<Messages> findById(@PathVariable Long post_id) {
        PostsResponseDto responseDto = postsService.findById(post_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Get Posts with id : " + post_id)
                .data(responseDto)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }

    @DeleteMapping("api/v1/posts/{post_id}")
    public ResponseEntity<Messages> delete(@PathVariable Long post_id) {
        Long deleted_id = postsService.delete(post_id);
        Messages messages = Messages.builder()
                .httpStatus(200)
                .message("Delete Posts with id : " + post_id)
                .data(deleted_id)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new ResponseEntity<>(messages, headers, HttpStatus.OK);
    }
}
