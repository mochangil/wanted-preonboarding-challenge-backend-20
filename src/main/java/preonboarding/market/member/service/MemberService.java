package preonboarding.market.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import preonboarding.market.member.dto.reqeust.MemberSaveRequestDto;
import preonboarding.market.member.entity.Member;
import preonboarding.market.member.repository.MemberRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void signUp(MemberSaveRequestDto memberSaveRequestDto){
        Optional<Member> foundMember = memberRepository.findByEmail(memberSaveRequestDto.email());
        if(foundMember.isPresent()){
            throw new IllegalArgumentException("이미 존재하는 회원 입니다.");
        }

        Member member = Member.from(memberSaveRequestDto);
        memberRepository.save(member);
    }
}
