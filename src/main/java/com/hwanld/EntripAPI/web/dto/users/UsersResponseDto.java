package com.hwanld.EntripAPI.web.dto.users;

import com.hwanld.EntripAPI.domain.planners.Planners;
//import com.hwanld.EntripAPI.domain.users.friends.Friends;
import com.hwanld.EntripAPI.domain.users.Users;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class UsersResponseDto {

    private String user_id;
    private Set<Planners> planners = new HashSet<Planners>();
    private String nickname;
    private int gender;
    private int travelFavorite;
    private String photoUrl;
    private String token;
    //private Set<Friends> friends = new HashSet<Friends>();
    //private Set<Users> friends = new HashSet<Users>();

    public UsersResponseDto (Users entity) {
        this.user_id = entity.getUser_id();
        this.planners = entity.getPlanners();
        this.nickname = entity.getNickname();
        this.gender = entity.getGender();
        this.travelFavorite = entity.getTravelFavorite();
        this.photoUrl = entity.getPhotoUrl();
        this.token = entity.getToken();
        //this.friends = entity.getFriends();
        //this.friends = entity.getFriends();
    }
}
