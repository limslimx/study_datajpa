package com.lim.datajpa.springboot.repository;

import com.lim.datajpa.springboot.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberJpaReqpositoryTest {

    @Autowired MemberJpaReqpository memberJpaReqpository;

    @Test
    public void testMember(){
        Member member=new Member("memberA");
        Member savedMember = memberJpaReqpository.save(member);


        Member findMember = memberJpaReqpository.find(member.getId());

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
        assertThat(findMember).isEqualTo(member);
    }
}