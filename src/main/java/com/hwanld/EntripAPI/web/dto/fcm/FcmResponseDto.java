package com.hwanld.EntripAPI.web.dto.fcm;

import com.hwanld.EntripAPI.domain.fcm.Fcm;
import lombok.Getter;

@Getter
public class FcmResponseDto {

    String user_id;
    String token;

    public FcmResponseDto (Fcm entity) {
        this.user_id = entity.getUser_id();
        this.token = entity.getToken();
    }
}
