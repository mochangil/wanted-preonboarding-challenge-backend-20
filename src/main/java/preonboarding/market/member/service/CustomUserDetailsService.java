package preonboarding.market.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import preonboarding.market.member.entity.Member;
import preonboarding.market.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email)
                .map(this::createUserDetails)
                .orElseThrow(() ->
                        new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));
    }


    private UserDetails createUserDetails(Member member){
        return User.builder()
                //username이지만, 우리는 커스텀으로 email
                .username(member.getUsername())
                //실제로는 DB에 PW 저장시 enncode해주기.
                .password(member.getPassword())
                .roles(member.getRoles().toArray(new String[0]))
                .build();
    }
}
