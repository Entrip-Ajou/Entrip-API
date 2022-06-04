package com.hwanld.EntripAPI.domain.posts;

import com.hwanld.EntripAPI.domain.BaseTimeEntity;
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
    private String author;
    private String title;
    private String content;

    @Builder
    public Posts (String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void update (String title, String content) {
        this.title = title;
        this.content = content;
    }
}
