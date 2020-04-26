package com.lim.datajpa.springboot.repository;

import com.lim.datajpa.springboot.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String userName, int age);
    List<Member> findHelloBy();
    List<Member> findTop3By();
}
