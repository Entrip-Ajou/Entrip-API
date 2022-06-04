package com.hwanld.EntripAPI.service.fcm;

import com.hwanld.EntripAPI.domain.fcm.Fcm;
import com.hwanld.EntripAPI.domain.fcm.FcmRepository;
import com.hwanld.EntripAPI.web.dto.fcm.FcmResponseDto;
import com.hwanld.EntripAPI.web.dto.fcm.FcmSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FcmService {

    private final FcmRepository fcmRepository;

    @Transactional
    public String save(FcmSaveRequestDto requestDto) {
        Fcm fcm = requestDto.toEntity();
        String user_id = fcmRepository.save(fcm).getUser_id();
        return user_id;
    }

    public FcmResponseDto findById (String user_id) {
        Fcm fcm = fcmRepository.findById(user_id).orElseThrow(
                ()->new IllegalArgumentException("Error raised at fcmRepository.findById, " + user_id)
        );
        return new FcmResponseDto(fcm);
    }

    public String getToken (String user_id) {
        FcmResponseDto responseDto = this.findById(user_id);
        String token = responseDto.getToken();
        return token;
    }
}
