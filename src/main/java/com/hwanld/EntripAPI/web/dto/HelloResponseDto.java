package com.hwanld.EntripAPI.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter //get 메소드 자동 생성
@RequiredArgsConstructor //생성자 자동 생성
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
