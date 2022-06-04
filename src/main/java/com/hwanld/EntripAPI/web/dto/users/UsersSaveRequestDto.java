package com.hwanld.EntripAPI.web.dto.users;

import com.hwanld.EntripAPI.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersSaveRequestDto {

    private String user_id;
    private String nickname;
    private int gender;
    private String photoUrl;

    @Builder
    public UsersSaveRequestDto (String user_id, String nickname, int gender, String photoUrl) {
        this.user_id = user_id;
        this.nickname = nickname;
        this.gender = gender;
        this.photoUrl = photoUrl;
    }

    public Users toEntity() {
        return Users.builder()
                .user_id(user_id)
                .nickname(nickname)
                .gender(gender)
                .photoUrl(photoUrl)
                .build();
    }
}
