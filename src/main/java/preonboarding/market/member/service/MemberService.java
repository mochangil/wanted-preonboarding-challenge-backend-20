package preonboarding.market.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import preonboarding.market.auth.dto.JwtToken;
import preonboarding.market.auth.jwt.JwtTokenProvider;
import preonboarding.market.member.dto.reqeust.MemberSaveRequestDto;
import preonboarding.market.member.entity.Member;
import preonboarding.market.member.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public JwtToken signIn(String email, String password){
        //1. username(email) + password 기반 Authentication 객체 생성
        //authentication 은 인증 여부 확인하는 authenticated 값 false
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(email, password);

        //2. 실제 검증. authenticated() 메서드를 통해 요청된 Member에 대한 검증 진행
        //authenticate 메서드 실행시, CustomUserDetailsService에서 만든 loadUserByUsername메서드 실행
        Authentication authentication =
                authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //3. 인증 정보 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

        return jwtToken;
    }

    public void signUp(MemberSaveRequestDto memberSaveRequestDto){
        Optional<Member> foundMember = memberRepository.findByEmail(memberSaveRequestDto.email());
        if(foundMember.isPresent()){
            throw new IllegalArgumentException("이미 존재하는 회원 입니다.");
        }
        //password encode
        String encodedPassword = passwordEncoder.encode(memberSaveRequestDto.password());

        List<String> roles = new ArrayList<>();
        roles.add("USER");

        Member member = Member.from(memberSaveRequestDto,encodedPassword,roles);
        memberRepository.save(member);
    }
}
