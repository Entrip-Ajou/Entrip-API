package com.hwanld.EntripAPI.domain.fcm;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class Fcm {

    @Id
    @Column(name = "user_id")
    private String user_id;

    @Column
    private String token;

    @Builder
    public Fcm (String user_id, String token) {
        this.user_id = user_id;
        this.token = token;
    }
}
