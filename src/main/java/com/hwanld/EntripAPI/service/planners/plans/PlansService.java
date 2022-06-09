package com.hwanld.EntripAPI.service.planners.plans;

import com.hwanld.EntripAPI.domain.planners.Planners;
import com.hwanld.EntripAPI.domain.planners.PlannersRepository;
import com.hwanld.EntripAPI.domain.planners.plans.Plans;
import com.hwanld.EntripAPI.domain.planners.plans.PlansRepository;
import com.hwanld.EntripAPI.domain.planners.plans.comments.Comments;
import com.hwanld.EntripAPI.domain.planners.plans.comments.CommentsRepository;
import com.hwanld.EntripAPI.service.planners.plans.comments.CommentsService;
import com.hwanld.EntripAPI.web.dto.planners.plans.PlansResponseDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.PlansSaveRequestDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.PlansUpdateRequestDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.comments.CommentsResponseDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.comments.CommentsReturnDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class PlansService {
    private final PlansRepository plansRepository;

    @Autowired
    private PlannersRepository plannersRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private CommentsService commentsService;

    @Transactional
    public Long save(PlansSaveRequestDto requestDto) {
        //PlansSaveRequestDto에 있는 Planners 불러오기
        Long planner_id = requestDto.getPlanner_id();
        Planners planners = plannersRepository.findById(planner_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at plannersRepository.findById, " + planner_id)
        );
        Plans plans = requestDto.toEntity();

        //Planners에 저장하는 Plans 추가하기
        planners.getPlans().add(plans);
        planners.setTimeStamp(LocalDateTime.now());
        //plans.getComments.setTimeStamp(LocalDateTime.now());

        //Save하는 Plans의 Planners 추가하기
        plans.setPlanners(planners);

        return plansRepository.save(plans).getPlan_id();
    }

    @Transactional
    public Long update (Long plan_id, PlansUpdateRequestDto requestDto) {
        //update 할 plans를 repository에서 불러오기
        Plans plans = plansRepository.findById(plan_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at plansRepository.findById, " + plan_id)
        );
        Planners planners = plans.getPlanners();
        planners.setTimeStamp(LocalDateTime.now());

        plans.update(requestDto.getDate(), requestDto.getTodo(), requestDto.getTime(),requestDto.getLocation(), requestDto.getRgb());
        return plan_id;
    }

    public PlansResponseDto findById(Long plan_id) {
        Plans plans = plansRepository.findById(plan_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at plansRepository.findById, " + plan_id)
        );
        return new PlansResponseDto(plans);
    }

    @Transactional
    public Long delete(Long plan_id) {
        Plans plans = plansRepository.findById(plan_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at plansRepository.findById, " + plan_id)
        );
        plans.getPlanners().getPlans().remove(plans);

        Iterator commentsIterator = plans.getComments().iterator();
        while(commentsIterator.hasNext()) {
            Comments comments = (Comments) commentsIterator.next();
            commentsIterator.remove();
            commentsService.delete(comments.getComment_id());
        }

        Planners planners = plans.getPlanners();
        planners.getPlans().remove(plans);
        planners.setTimeStamp(LocalDateTime.now());

        plansRepository.delete(plans);
        return plan_id;
    }


}
