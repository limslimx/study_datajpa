package com.lim.datajpa.springboot.repository;

import com.lim.datajpa.springboot.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String userName, int age);

    List<Member> findHelloBy();

    List<Member> findTop3By();

//    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);
}
