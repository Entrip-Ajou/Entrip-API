package com.hwanld.EntripAPI.domain.sockets;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Greeting {

    private String content;

    @Builder
    public Greeting (String content) {
        this.content = content;
    }
}
