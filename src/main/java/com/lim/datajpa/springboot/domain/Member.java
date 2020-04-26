package com.lim.datajpa.springboot.domain;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@ToString(of = {"id", "userName", "age"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userName;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String userName){
        this.userName=userName;
    }

    public Member(String userName, int age, Team team) {
        this.userName=userName;
        this.age=age;
        if(team!=null){
            changeTeam(team);
        }
    }

    public void changeTeam(Team team){
        this.team=team;
        team.getMembers().add(this);
    }
}
