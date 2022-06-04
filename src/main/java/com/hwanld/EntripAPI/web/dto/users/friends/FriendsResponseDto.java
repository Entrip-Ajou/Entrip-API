package com.hwanld.EntripAPI.web.dto.users.friends;

import com.hwanld.EntripAPI.domain.users.Users;
import com.hwanld.EntripAPI.domain.users.friends.Friends;
import lombok.Getter;

@Getter
public class FriendsResponseDto {

    private Long friends_id;
    private Users users;
    private String anotherUsers_id;

    public FriendsResponseDto (Friends entity) {
        this.friends_id = entity.getFriends_id();
        this.users = entity.getUsers();
        this.anotherUsers_id = entity.getAnotherUsers_id();
    }
}
