package hello2.hellospring2.service;

import hello2.hellospring2.domain.Member;
import hello2.hellospring2.repository.MemberRepository;
import hello2.hellospring2.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }


    public Long join(Member member){
        //같은 이름 중복회원 ㄴㄴ
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName());
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(membe->{
            throw new IllegalStateException("이미 존재합니다");
        });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
