package com.hwanld.EntripAPI.service.users;

import com.hwanld.EntripAPI.domain.exceptions.NicknameOrUserIdNotValidException;
import com.hwanld.EntripAPI.domain.planners.Planners;
import com.hwanld.EntripAPI.domain.planners.PlannersRepository;
import com.hwanld.EntripAPI.domain.users.Users;
import com.hwanld.EntripAPI.domain.users.UsersRepository;
import com.hwanld.EntripAPI.web.dto.planners.PlannersResponseDto;
import com.hwanld.EntripAPI.web.dto.planners.PlannersReturnDto;
import com.hwanld.EntripAPI.web.dto.users.UsersResponseDto;
import com.hwanld.EntripAPI.web.dto.users.UsersSaveRequestDto;
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
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    private PlannersRepository plannersRepository;

    @Transactional
    public String save(UsersSaveRequestDto requestDto) {
        Users users = Users.builder()
                .user_id(requestDto.getUser_id())
                .photoUrl(requestDto.getPhotoUrl())
                .gender(requestDto.getGender())
                .nickname(requestDto.getNickname())
                .build();
        String user_id = usersRepository.save(users).getUser_id();
        return user_id;
    }

    public UsersResponseDto findByUserId(String user_id) {
        Users entity = usersRepository.findById(user_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at usersRepository.findById, "+user_id)
        );
        return new UsersResponseDto(entity);
    }

    @Transactional
    public String delete (String user_id) {
        Users users = usersRepository.findById(user_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at usersRepository.findById, "+user_id)
        );
        //Erase users from the planner which has planners
        Iterator plannersIterator = users.getPlanners().iterator();
        while(plannersIterator.hasNext()) {
            Planners planners = (Planners) plannersIterator.next();
            planners.getUsers().remove(users);
        }
        usersRepository.delete(users);
        return user_id;
    }

    @Transactional
    public Long addPlanners (Long planner_id, String user_id) {
        Planners planners = plannersRepository.findById(planner_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at plannersRepository.findById, "+planner_id)
        );
        Users users = usersRepository.findById(user_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at usersRepository.findById, "+user_id)
        );
        users.addPlanners(planners);
        planners.setTimeStamp(LocalDateTime.now());
        return planners.getPlanner_id();
    }

    @Transactional
    public List<PlannersReturnDto> findAllPlannersWithUsersId (String user_id) {
        Users users = usersRepository.findById(user_id).orElseThrow(
                ()->new IllegalArgumentException("Error raise at usersRepository.findById, "+user_id)
        );
        Set<Planners> plannersSet = users.getPlanners();
        List<PlannersReturnDto> plannersList = new ArrayList<PlannersReturnDto>();
        Iterator iterator = plannersSet.iterator();

        while(iterator.hasNext()) {
            Planners planners = (Planners) iterator.next();
            PlannersResponseDto responseDto = new PlannersResponseDto(planners);
            PlannersReturnDto returnDto = new PlannersReturnDto(responseDto);
            plannersList.add(returnDto);
        }
        return plannersList;
    }

    public Boolean isExistNickName (String nickname) {
        List<Users> usersList = usersRepository.findAll();
        for (Users users : usersList) {
            String temp = users.getNickname();
            if (temp.equals(nickname)) return true;
        }
        return false;
    }

    public Boolean isExistUserId (String user_id) {
        List<Users> usersList = usersRepository.findAll();
        for (Users users : usersList) {
            String temp = users.getUser_id();
            if (temp.equals(user_id)) return true;
        }
        return false;
    }

    @Transactional
    public String updateToken (String user_id, String token) {
        Users users = usersRepository.findById(user_id).orElseThrow(
                ()->new IllegalArgumentException("Error raised at usersRepository.findById, " + user_id)
        );
        users.updateToken(token);
        return user_id;
    }

    public String findUserWithNicknameOrUserId (String nicknameOrUserId) throws NicknameOrUserIdNotValidException {
        List<Users> usersList = usersRepository.findAll();
        for (Users users : usersList) {
            if (users.getUser_id().equals(nicknameOrUserId) || users.getNickname().equals(nicknameOrUserId))
                return users.getUser_id();
        }
        throw new NicknameOrUserIdNotValidException(nicknameOrUserId);
    }

    @Transactional
    public String updateTravelFavorite (String user_id, String travelFavorite) {
        Users users = usersRepository.findById(user_id).orElseThrow(
                ()->new IllegalArgumentException("Error raised at usersRepository.findById, " + user_id)
        );
        String result = users.updateTravelFavorite(travelFavorite);
        return result;
    }
}
