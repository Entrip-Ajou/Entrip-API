package com.hwanld.EntripAPI.domain.plans;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Plans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hash_value;

    @Column
    private String name;
    private String start_time;
    private String end_time;
    private String location;

    @Column(nullable = false)
    private String author;

    @Builder
    public Plans (String name, String start_time, String end_time, String location, String author) {
        this.name = name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.location = location;
        this.author = author;
    }

    public void update (String name, String start_time, String end_time, String location) {
        this.name = name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.location = location;
    }
}
