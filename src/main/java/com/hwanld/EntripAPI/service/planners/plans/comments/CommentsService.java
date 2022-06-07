package com.hwanld.EntripAPI.service.planners.plans.comments;

import com.hwanld.EntripAPI.domain.planners.Planners;
import com.hwanld.EntripAPI.domain.planners.PlannersRepository;
import com.hwanld.EntripAPI.domain.planners.plans.Plans;
import com.hwanld.EntripAPI.domain.planners.plans.PlansRepository;
import com.hwanld.EntripAPI.domain.planners.plans.comments.Comments;
import com.hwanld.EntripAPI.domain.planners.plans.comments.CommentsRepository;
import com.hwanld.EntripAPI.domain.users.Users;
import com.hwanld.EntripAPI.domain.users.UsersRepository;
import com.hwanld.EntripAPI.service.planners.plans.PlansService;
import com.hwanld.EntripAPI.web.dto.planners.plans.comments.CommentsResponseDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.comments.CommentsReturnDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.comments.CommentsSaveRequestDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.comments.CommentsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;

    @Autowired
    private PlansService plansService;

    @Autowired
    private PlannersRepository plannersRepository;

    @Autowired
    private PlansRepository plansRepository;

    @Autowired
    private UsersRepository usersRepository;


    @Transactional
    public List<CommentsReturnDto> save(CommentsSaveRequestDto requestDto) {
        //저장하려는 plans 불러오기
        Plans plans = plansRepository.findById(requestDto.getPlans_id()).orElseThrow(
                ()->new IllegalArgumentException("Error raised at plansRepository.findById, " + requestDto.getPlans_id())
        );
        //저장하는 User 불러오기
        Users users = usersRepository.findById(requestDto.getAuthor()).orElseThrow(
                ()->new IllegalArgumentException("Error raised at usersRepository.findById, " + requestDto.getAuthor())
        );
        //저장하려는 comments 만들기
        Comments comments = requestDto.toEntity();
        //comments에 plans 추가하기
        comments.setPlans(plans);
        //comments에 users 추가하기
        comments.setUsers(users);
        //comments 저장하기
        commentsRepository.save(comments);
        //plans에 comments 추가하기
        plans.getComments().add(comments);
        //planners의 comment_timeStamp 초기화
        plans.getPlanners().setComment_timeStamp();

        return getAllCommentsWithPlanId(requestDto.getPlans_id());
    }

    @Transactional
    public List<CommentsReturnDto> update(Long comment_id, CommentsUpdateRequestDto requestDto) {
        Comments comments = commentsRepository.findById(comment_id).orElseThrow(
                ()-> new IllegalArgumentException("Error raised at CommentsRepository.findById, " + comment_id)
        );
        comments.update(requestDto.getAuthor(),requestDto.getContent());
        //planners의 comment_timeStamp 초기화
        comments.getPlans().getPlanners().setComment_timeStamp();
        return getAllCommentsWithPlanId(requestDto.getPlans_id());
    }

    public CommentsResponseDto findById(Long comment_id) {
        Comments comments = commentsRepository.findById(comment_id).orElseThrow(
                ()-> new IllegalArgumentException("Error raised at CommentsRepository.findById, " + comment_id)
        );
        return new CommentsResponseDto(comments);
    }

    @Transactional
    public List<CommentsReturnDto> delete(Long comment_id) {
        Comments comments = commentsRepository.findById(comment_id).orElseThrow(
                ()-> new IllegalArgumentException("Error raised at CommentsRepository.findById, " + comment_id)
        );
        Long plan_id = comments.getPlans().getPlan_id();
        comments.getPlans().getPlanners().setComment_timeStamp();
        comments.getPlans().getComments().remove(comments);
        commentsRepository.delete(comments);
        return getAllCommentsWithPlanId(plan_id);
    }

    public List<CommentsReturnDto> getAllCommentsWithPlanId (Long plan_id) {
        Plans plans = plansRepository.findById(plan_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at plansRepository.findById, " + plan_id)
        );
        Set<Comments> commentsSet = plans.getComments();
        List<CommentsReturnDto> commentsList = new ArrayList<CommentsReturnDto>();
        Iterator iterator = commentsSet.iterator();

        while(iterator.hasNext()) {
            Comments comments = (Comments) iterator.next();
            CommentsResponseDto responseDto = new CommentsResponseDto(comments);
            CommentsReturnDto returnDto = new CommentsReturnDto(responseDto);
            commentsList.add(returnDto);
        }
        return commentsList;
    }
}
