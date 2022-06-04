package com.hwanld.EntripAPI.service.planners.plans.comments;

import com.hwanld.EntripAPI.domain.planners.Planners;
import com.hwanld.EntripAPI.domain.planners.PlannersRepository;
import com.hwanld.EntripAPI.domain.planners.plans.Plans;
import com.hwanld.EntripAPI.domain.planners.plans.PlansRepository;
import com.hwanld.EntripAPI.domain.planners.plans.comments.Comments;
import com.hwanld.EntripAPI.domain.planners.plans.comments.CommentsRepository;
import com.hwanld.EntripAPI.web.dto.planners.plans.comments.CommentsResponseDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.comments.CommentsSaveRequestDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.comments.CommentsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;

    @Autowired
    private PlannersRepository plannersRepository;

    @Autowired
    private PlansRepository plansRepository;

    @Transactional
    public Long save(CommentsSaveRequestDto requestDto) {
        //저장하려는 plans 불러오기
        Plans plans = plansRepository.findById(requestDto.getPlans_id()).orElseThrow(
                ()->new IllegalArgumentException("Error raised at plansRepository.findById, " + requestDto.getPlans_id())
        );
        //저장하려는 comments 만들기
        Comments comments = requestDto.toEntity();
        //comments에 plans 추가하기
        comments.setPlans(plans);
        //comments 저장하기
        commentsRepository.save(comments);
        //plans에 comments 추가하기
        plans.getComments().add(comments);
        //planners의 comment_timeStamp 초기화
        plans.getPlanners().setComment_timeStamp();

        return comments.getComment_id();
    }

    @Transactional
    public Long update(Long comment_id, CommentsUpdateRequestDto requestDto) {
        Comments comments = commentsRepository.findById(comment_id).orElseThrow(
                ()-> new IllegalArgumentException("Error raised at CommentsRepository.findById, " + comment_id)
        );
        comments.update(requestDto.getAuthor(),requestDto.getContent());
        //planners의 comment_timeStamp 초기화
        comments.getPlans().getPlanners().setComment_timeStamp();
        return comment_id;
    }

    public CommentsResponseDto findById(Long comment_id) {
        Comments comments = commentsRepository.findById(comment_id).orElseThrow(
                ()-> new IllegalArgumentException("Error raised at CommentsRepository.findById, " + comment_id)
        );
        return new CommentsResponseDto(comments);
    }

    @Transactional
    public Long delete(Long comment_id) {
        Comments comments = commentsRepository.findById(comment_id).orElseThrow(
                ()-> new IllegalArgumentException("Error raised at CommentsRepository.findById, " + comment_id)
        );
        comments.getPlans().getPlanners().setComment_timeStamp();
        comments.getPlans().getComments().remove(comments);
        commentsRepository.delete(comments);
        return comment_id;
    }
}
