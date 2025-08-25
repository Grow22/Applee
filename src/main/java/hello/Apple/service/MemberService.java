package hello.Apple.service;

import hello.Apple.domain.Member;
//import hello.Apple.repository.MemberRepository;
import hello.Apple.repository.SpringDataJpaMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final SpringDataJpaMemberRepository memberRepository;

    // 회원가입 메서드
    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    public Optional<Member> findByName(String name) {
        return memberRepository.findByName(name);
    }
}


