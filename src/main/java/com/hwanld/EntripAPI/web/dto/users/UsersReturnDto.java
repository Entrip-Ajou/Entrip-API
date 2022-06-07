package com.hwanld.EntripAPI.web.dto.users;

//import com.hwanld.EntripAPI.domain.users.friends.Friends;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class UsersReturnDto {

    private String user_id;
    private String nickname;
    private int gender;
    private String travelFavorite;
    private String photoUrl;
    private String token;
    //private Set<Friends> friends = new HashSet<Friends>();

    public UsersReturnDto (UsersResponseDto responseDto) {
        this.user_id = responseDto.getUser_id();
        this.nickname = responseDto.getNickname();
        this.gender = responseDto.getGender();
        this.travelFavorite = responseDto.getTravelFavorite();
        this.photoUrl = responseDto.getPhotoUrl();
        this.token = responseDto.getToken();
        //this.friends = responseDto.getFriends();
    }
}
