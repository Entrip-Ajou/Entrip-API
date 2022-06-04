package com.hwanld.EntripAPI.domain.sockets;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Chat {
    private String name;
    private String message;

    @Builder
    public Chat(String name, String message) {
        this.name = name;
        this.message = message;
    }
}
