package com.hwanld.EntripAPI.domain.sockets;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HelloMessage {

    private String name;

    @Builder
    public HelloMessage(String name) {
        this.name = name;
    }
}
