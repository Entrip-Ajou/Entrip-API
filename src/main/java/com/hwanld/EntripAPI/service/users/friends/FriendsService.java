package com.hwanld.EntripAPI.service.users.friends;

import com.hwanld.EntripAPI.domain.users.Users;
import com.hwanld.EntripAPI.domain.users.UsersRepository;
import com.hwanld.EntripAPI.domain.users.friends.Friends;
import com.hwanld.EntripAPI.domain.users.friends.FriendsRepository;
import com.hwanld.EntripAPI.web.dto.users.friends.FriendsResponseDto;
import com.hwanld.EntripAPI.web.dto.users.friends.FriendsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FriendsService {

    private final FriendsRepository friendsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    public Long save (FriendsSaveRequestDto requestDto) {
        //Users 찾기
        Users users = usersRepository.findById(requestDto.getUsers_id()).orElseThrow(
                ()->new IllegalArgumentException("Error raised at usersRepository.findById, " + requestDto.getUsers_id())
        );
        //Friends 만들기
        Friends friends = Friends.builder()
                .anotherUsers_id(requestDto.getAnotherUsers_id())
                .build();
        //Friends에 Users 추가하기
        friends.setUsers(users);
        //Users에 Friends 추가하기
        users.getFriends().add(friends);
        //FriendsRepository에 저장하기
        friendsRepository.save(friends);

        return friends.getFriends_id();
    }

    public FriendsResponseDto findById (Long friends_id) {
        Friends friends = friendsRepository.findById(friends_id).orElseThrow(
                ()->new IllegalArgumentException("Error raised at friendsRepository.findById, " + friends_id)
        );
        return new FriendsResponseDto(friends);
    }

}
