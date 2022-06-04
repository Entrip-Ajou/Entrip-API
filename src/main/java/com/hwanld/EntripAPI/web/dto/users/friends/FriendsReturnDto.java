package com.hwanld.EntripAPI.web.dto.users.friends;

import lombok.Getter;

@Getter
public class FriendsReturnDto {

    private String users_Id;
    private String anotherUsers_id;
    private Long friends_id;

    public FriendsReturnDto (FriendsResponseDto responseDto) {
        this.users_Id = responseDto.getUsers().getUser_id();
        this.anotherUsers_id = responseDto.getAnotherUsers_id();
        this.friends_id = responseDto.getFriends_id();
    }
}
