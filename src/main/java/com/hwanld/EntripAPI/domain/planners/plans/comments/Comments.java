package com.hwanld.EntripAPI.domain.planners.plans.comments;

import com.hwanld.EntripAPI.domain.BaseTimeEntity;
import com.hwanld.EntripAPI.domain.planners.plans.Plans;
import com.hwanld.EntripAPI.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @Column
    private String author;
    private String content;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PLAN_PLANS_ID")
    private Plans plans;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_USERS_ID")
    private Users users;

    @Builder
    public Comments (Long comment_id, String author, String content, Long planner_id) {
        this.comment_id = comment_id;
        this.author = author;
        this.content = content;
    }

    public void update (String author, String content) {
        this.author = author;
        this.content = content;
    }

    public Long setPlans(Plans plans) {
        this.plans = plans;
        return this.plans.getPlan_id();
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
