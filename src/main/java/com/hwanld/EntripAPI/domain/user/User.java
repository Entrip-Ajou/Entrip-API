package com.hwanld.EntripAPI.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor

public class User {
    private String id;
    private String name;
    private String picture;
    private String email;
}
