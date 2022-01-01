package hello2.hellospring2.repository;

import hello2.hellospring2.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository =new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member=new Member();
        member.setName("spr");

        repository.save(member);

        Member result =repository.findById(member.getId()).get();

        System.out.println("result = "+(result==member));
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1=new Member();
        member1.setName("seep");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("seep2");
        repository.save(member2);

        Member result= repository.findByName("seep2").get();

        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("seep");
        repository.save(member1);

        Member member2=new Member();
        member2.setName("seep2");
        repository.save(member2);

        List<Member> result= repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
