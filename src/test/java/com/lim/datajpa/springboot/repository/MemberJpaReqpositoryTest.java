package com.lim.datajpa.springboot.repository;

import com.lim.datajpa.springboot.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//@Rollback(false)
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
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void basicCRUD(){
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        memberJpaReqpository.save(member1);
        memberJpaReqpository.save(member2);

        Member findMember1 = memberJpaReqpository.findById(member1.getId()).get();
        Member findMember2 = memberJpaReqpository.findById(member2.getId()).get();

        //단건조회 검증
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        //리스트 조회 검증
        List<Member> all = memberJpaReqpository.findAll();
        assertThat(all.size()).isEqualTo(2);

        //카운트 검증
        long count = memberJpaReqpository.count();
        assertThat(count).isEqualTo(2);

        //삭제 검증
        memberJpaReqpository.delete(member1);
        memberJpaReqpository.delete(member2);
        long deletedCount = memberJpaReqpository.count();
        assertThat(deletedCount).isEqualTo(0);
    }

    @Test
    public void findByUsernameAndAgeGreaterThan(){
        Member member1=new Member("aaa", 10);
        Member member2=new Member("aaa", 20);
        memberJpaReqpository.save(member1);
        memberJpaReqpository.save(member2);

        List<Member> members = memberJpaReqpository.findByUsernameAndAgeGreaterThan("aaa", 15);
        assertThat(members.get(0).getUsername()).isEqualTo(member2.getUsername());
        assertThat(members.get(0).getAge()).isEqualTo(20);
        assertThat(members.size()).isEqualTo(1);
    }

    @Test
    public void testNamedQuery(){
        Member member1=new Member("aaa", 10);
        Member member2=new Member("bbb", 20);
        memberJpaReqpository.save(member1);
        memberJpaReqpository.save(member2);

        List<Member> result = memberJpaReqpository.findByUsername(member1.getUsername());
        Member findMember = result.get(0);
        assertThat(findMember).isEqualTo(member1);
    }

    @Test
    public void paging(){
        //given
        memberJpaReqpository.save(new Member("member1", 10));
        memberJpaReqpository.save(new Member("member2", 10));
        memberJpaReqpository.save(new Member("member3", 10));
        memberJpaReqpository.save(new Member("member4", 10));
        memberJpaReqpository.save(new Member("member5", 10));

        int age=10;
        int offset=0;
        int limit=3;

        //when
        List<Member> members = memberJpaReqpository.findByPage(age, offset, limit);
        long totalCount = memberJpaReqpository.totalCount(age);

        //then
        System.out.println(members.get(0).getUsername());
        System.out.println(members.get(1).getUsername());
        System.out.println(members.get(2).getUsername());
        assertThat(members.size()).isEqualTo(3);
        assertThat(totalCount).isEqualTo(5);

    }
}