package com.hwanld.EntripAPI.web.dto.fcm;

import com.hwanld.EntripAPI.domain.fcm.Fcm;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FcmSaveRequestDto {
    String user_id;
    String token;

    @Builder
    public FcmSaveRequestDto (String user_id, String token) {
        this.user_id = user_id;
        this.token = token;
    }

    public Fcm toEntity() {
        return Fcm.builder()
                .user_id(user_id)
                .token(token)
                .build();
    }
}
