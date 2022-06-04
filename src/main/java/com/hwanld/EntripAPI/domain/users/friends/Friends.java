package com.hwanld.EntripAPI.domain.users.friends;

import com.hwanld.EntripAPI.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.h2.engine.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friends_id;

    @ManyToOne
    @JoinColumn(name = "USER_USERS_ID")
    private Users users;

    @Column
    private String anotherUsers_id;

    @Builder
    public Friends (String anotherUsers_id) {
        this.anotherUsers_id = anotherUsers_id;
    }

    public String setUsers (Users users) {
        this.users = users;
        return users.getUser_id();
    }
}
