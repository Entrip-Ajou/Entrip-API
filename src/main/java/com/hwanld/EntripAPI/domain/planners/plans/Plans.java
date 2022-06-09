package com.hwanld.EntripAPI.domain.planners.plans;

import com.hwanld.EntripAPI.domain.planners.Planners;
import com.hwanld.EntripAPI.domain.planners.plans.comments.Comments;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Plans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plan_id;

    @Column
    private String date;
    private String todo;
    private String time;
    private String location;
    private Long rgb;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PLANNER_PLANNERS_ID")
    private Planners planners;


    @Column
    @OneToMany(mappedBy = "plans", fetch = FetchType.EAGER)
    private Set<Comments> comments = new HashSet<>();

    @Builder
    public Plans (String date, String todo, String time, String location, Long rgb) {
        this.date = date;
        this.todo = todo;
        this.time = time;
        this.location = location;
        this.rgb = rgb;
    }

    public void update (String date, String todo, String time, String location, Long rgb) {
        this.date = date;
        this.todo = todo;
        this.time = time;
        this.location = location;
        this.rgb = rgb;
    }

    public Long setPlanners(Planners planners) {
        this.planners = planners;
        return this.planners.getPlanner_id();
    }

    public Boolean isExistComments() {
        return comments.isEmpty();
    }

}
