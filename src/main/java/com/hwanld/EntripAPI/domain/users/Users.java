package com.hwanld.EntripAPI.domain.users;

import com.hwanld.EntripAPI.domain.BaseTimeEntity;
import com.hwanld.EntripAPI.domain.planners.Planners;
import com.hwanld.EntripAPI.domain.planners.plans.comments.Comments;
import com.hwanld.EntripAPI.domain.posts.Posts;
import com.hwanld.EntripAPI.domain.users.friends.Friends;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Users extends BaseTimeEntity {

    @Id @Column(name = "user_id")
    private String user_id;

    @Column
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "PLANNERS_USERS")
    private Set<Planners> planners = new HashSet<Planners>();

    @Column
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Friends> friends = new HashSet<Friends>();

    @Column
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Posts> posts = new HashSet<Posts>();

    @Column
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Comments> comments = new HashSet<Comments>();

    @Column(nullable = false)
    private String nickname;

    @Column
    private int gender;
    private String travelFavorite;
    private String photoUrl;
    private String token;

    @Builder
    public Users (String user_id, String nickname, int gender, String travelFavorite, String photoUrl) {
        this.user_id = user_id;
        this.nickname = nickname;
        this.gender = gender;
        this.travelFavorite = travelFavorite;
        this.photoUrl = photoUrl;
    }

    public Long addPlanners(Planners planners) {
        this.planners.add(planners);
        return planners.getPlanner_id();
    }

    public String updateToken(String token) {
        this.token = token;
        return this.token;
    }

    public String updateTravelFavorite (String travelFavorite) {
        this.travelFavorite = travelFavorite;
        return this.travelFavorite;
    }

    public void addPosts(Posts posts) {
        this.posts.add(posts);
    }

}
