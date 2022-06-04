package com.hwanld.EntripAPI.web.dto.users.friends;

import com.hwanld.EntripAPI.domain.users.friends.Friends;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendsSaveRequestDto {

    private String users_id;
    private String anotherUsers_id;

    @Builder
    public FriendsSaveRequestDto (String users_id, String anotherUsers_id) {
        this.users_id = users_id;
        this.anotherUsers_id = anotherUsers_id;
    }

    public Friends toEntity() {
        return Friends.builder()
                .anotherUsers_id(anotherUsers_id)
                .build();
    }
}
