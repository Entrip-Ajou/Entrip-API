package com.hwanld.EntripAPI.domain.planners;

import com.hwanld.EntripAPI.domain.BaseTimeEntity;
import com.hwanld.EntripAPI.domain.planners.plans.Plans;
import com.hwanld.EntripAPI.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Planners extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planner_id;

    @Column
    private String title;
    private String start_date;
    private String end_date;
    private LocalDateTime comment_timeStamp;

    @Column
    @OneToMany(mappedBy = "planners", fetch = FetchType.EAGER)
    private Set<Plans> plans  = new HashSet<Plans>();

    @Column
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERS_PLANNERS")
    private Set<Users> users = new HashSet<Users>();

    @Builder
    public Planners (String title, String start_date, String end_date, String time_stamp) {
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public void update (String title, String start_date, String end_date) {
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String addUsers(Users users){
        this.users.add(users);
        return users.getUser_id();
    }

    public void setComment_timeStamp(){
        this.comment_timeStamp = LocalDateTime.now();
    }

}
