package com.hwanld.EntripAPI.service.planners;

import com.hwanld.EntripAPI.domain.planners.Planners;
import com.hwanld.EntripAPI.domain.planners.PlannersRepository;
import com.hwanld.EntripAPI.domain.planners.plans.Plans;
import com.hwanld.EntripAPI.domain.planners.plans.PlansRepository;
import com.hwanld.EntripAPI.domain.planners.plans.comments.Comments;
import com.hwanld.EntripAPI.domain.users.Users;
import com.hwanld.EntripAPI.domain.users.UsersRepository;
import com.hwanld.EntripAPI.service.planners.plans.PlansService;
import com.hwanld.EntripAPI.service.planners.plans.comments.CommentsService;
import com.hwanld.EntripAPI.web.dto.planners.PlannersResponseDto;
import com.hwanld.EntripAPI.web.dto.planners.PlannersSaveRequestDto;
import com.hwanld.EntripAPI.web.dto.planners.PlannersUpdateRequestDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.PlansResponseDto;
import com.hwanld.EntripAPI.web.dto.planners.plans.PlansReturnDto;
import com.hwanld.EntripAPI.web.dto.users.UsersResponseDto;
import com.hwanld.EntripAPI.web.dto.users.UsersReturnDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service
public class PlannersService {
    private final PlannersRepository plannersRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PlansRepository plansRepository;

    @Autowired
    private PlansService plansService;

    @Autowired
    private CommentsService commentsService;

    @Transactional
    public Long save(PlannersSaveRequestDto requestDto) {
        //PlannersSaveRequestDto에 있는 Users, Planners 불러오기
        String user_id = requestDto.getUser_id();
        Users users = usersRepository.findById(user_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at usersRepository.findById, " + user_id)
        );
        Planners planners = requestDto.toEntity();

        //planner 객체의 comment_timeStamp LocalDateTime.now()로 초기화
        planners.setComment_timeStamp();


        //Save할 때 Users를 자동으로 추가하기
        users.addPlanners(planners);

        planners.addUsers(users);

        return plannersRepository.save(planners).getPlanner_id();
    }

    @Transactional
    public Long update (Long planner_id, PlannersUpdateRequestDto requestDto) {
        //update 할 planners를 repository에서 불러오기
        Planners planners = plannersRepository.findById(planner_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at plannersRepository.findById, "+planner_id)
        );
        planners.update(requestDto.getTitle(), requestDto.getStart_date(), requestDto.getEnd_date());
        return planner_id;
    }

    public PlannersResponseDto findByPlannerId(Long planner_id) {
        Planners entity = plannersRepository.findById(planner_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at plannersRepository.findById, "+planner_id)
        );
        return new PlannersResponseDto(entity);
    }

    public List<PlansReturnDto> findByPlannerIdWithDate(Long planner_id, String date) {
        Planners planners = plannersRepository.findById(planner_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at plannersRepository.findById, "+planner_id)
        );
        Set<Plans> plansSet = planners.getPlans();
        List<PlansReturnDto> plansList = new ArrayList<PlansReturnDto>();
        Iterator iterator = plansSet.iterator();

        while(iterator.hasNext()) {
            Plans plans = (Plans) iterator.next();
            if (plans.getDate().equals(date)) {
                PlansResponseDto responseDto = new PlansResponseDto(plans);
                PlansReturnDto returnDto = new PlansReturnDto(responseDto);
                plansList.add(returnDto);
            }
        }
        return plansList;
    }

    public List<PlansReturnDto> findAllPlansWithPlannerId (Long planner_id) {
        Planners planners = plannersRepository.findById(planner_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at plannersRepository.findById, "+planner_id)
        );
        Set<Plans> plansSet = planners.getPlans();
        List<PlansReturnDto> plansList = new ArrayList<PlansReturnDto>();
        Iterator iterator = plansSet.iterator();

        while(iterator.hasNext()) {
            Plans plans = (Plans) iterator.next();
            PlansResponseDto responseDto = new PlansResponseDto(plans);
            PlansReturnDto returnDto = new PlansReturnDto(responseDto);
            plansList.add(returnDto);
        }
        return plansList;
    }

    //Notion
    public List<UsersReturnDto> findAllUsersWithPlannerId (Long planner_id) {
        Planners planners = plannersRepository.findById(planner_id).orElseThrow(
                ()-> new IllegalArgumentException("Error raise at plannersRepository.findById, " +planner_id)
        );
        List<UsersReturnDto> usersList = new ArrayList<UsersReturnDto>();
        Set<Users> usersSet = planners.getUsers();
        Iterator iterator = usersSet.iterator();

        while(iterator.hasNext()) {
            Users users = (Users) iterator.next();
            UsersResponseDto responseDto = new UsersResponseDto(users);
            UsersReturnDto returnDto = new UsersReturnDto(responseDto);
            usersList.add(returnDto);
        }
        return usersList;
    }

    public Boolean plannerIsExistWithId (Long planner_id) {
        return plannersRepository.existsById(planner_id);
    }

    @Transactional
    public Long delete(Long planner_id) {
        Planners planners = plannersRepository.findById(planner_id).orElseThrow(
                () -> new IllegalArgumentException("Error raise at plannersRepository.findById, " + planner_id)
        );

        Iterator plansIterator = planners.getPlans().iterator();
        while(plansIterator.hasNext()) {
            Plans plans = (Plans) plansIterator.next();

            Iterator commentsIterator = plans.getComments().iterator();
            while(commentsIterator.hasNext()) {
                Comments comments = (Comments) commentsIterator.next();
                commentsService.delete(comments.getComment_id());
            }

            plansRepository.delete(plans);
        }

        Iterator usersIterator = planners.getUsers().iterator();
        while(usersIterator.hasNext()) {
            Users users = (Users) usersIterator.next();
            users.getPlanners().remove(planners);
        }

        plannersRepository.delete(planners);
        return planner_id;
    }

    @Transactional
    public String addFriendToPlanner (Long planner_id, String user_id) {
        Planners planners = plannersRepository.findById(planner_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at plannersRepository.findById, "+ planner_id)
        );
        Users users = usersRepository.findById(user_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at usersRepository.findById, "+user_id)
        );
        if (planners.getUsers().contains(users)) return "이미 planner에 등록되어있는 회원입니다!";
            //나중에 ifPlannerContainUser 메소드로 구현하기
        planners.addUsers(users);
        users.getPlanners().add(planners);
        return planner_id + "번 플래너에 " + user_id + "사용자 등록 완료";
    }

    public Boolean userIsExistWithPlanner (Long planner_id, String user_id) {
        Planners planners = plannersRepository.findById(planner_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at plannersRepository.findById, "+ planner_id)
        );
        Users users = usersRepository.findById(user_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at usersRepository.findById, "+user_id)
        );
        if (planners.getUsers().contains(users)) return true;
        return false;
    }
}
