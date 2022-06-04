package com.hwanld.EntripAPI.service.posts;

import com.hwanld.EntripAPI.domain.posts.Posts;
import com.hwanld.EntripAPI.domain.posts.PostsRepository;
import com.hwanld.EntripAPI.web.dto.posts.PostsResponseDto;
import com.hwanld.EntripAPI.web.dto.posts.PostsSaveRequestDto;
import com.hwanld.EntripAPI.web.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        Posts posts = requestDto.toEntity();
        Long post_id = postsRepository.save(posts).getPost_id();
        return post_id;
    }

    @Transactional
    public Long update(Long post_id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(post_id).orElseThrow(
                ()->new IllegalArgumentException("Error raised at postsRepository.findById, " + post_id)
        );
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return post_id;
    }

    public PostsResponseDto findById(Long post_id) {
        Posts posts = postsRepository.findById(post_id).orElseThrow(
                ()->new IllegalArgumentException("Error raised at postsRepository.findById, " + post_id)
        );
        return new PostsResponseDto(posts);
    }

    @Transactional
    public Long delete (Long post_id) {
        Posts posts = postsRepository.findById(post_id).orElseThrow(
                ()->new IllegalArgumentException("Error raised at postsRepository.findById, " + post_id)
        );
        postsRepository.delete(posts);
        return post_id;
    }
}
