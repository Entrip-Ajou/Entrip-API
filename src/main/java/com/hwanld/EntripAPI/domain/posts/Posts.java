package com.hwanld.EntripAPI.domain.posts;

import com.hwanld.EntripAPI.domain.BaseTimeEntity;
import com.hwanld.EntripAPI.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long post_id;

    @Column
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_USERS_ID")
    private Users users;

    @Builder
    public Posts (String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update (String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}
