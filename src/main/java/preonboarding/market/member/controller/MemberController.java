package preonboarding.market.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import preonboarding.market.auth.config.SecurityUtil;
import preonboarding.market.auth.dto.JwtToken;
import preonboarding.market.auth.dto.LoginRequest;
import preonboarding.market.member.dto.reqeust.MemberSaveRequestDto;
import preonboarding.market.member.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("save")
    public void saveMember(@RequestBody MemberSaveRequestDto memberSaveRequestDto){
        memberService.signUp(memberSaveRequestDto);
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody MemberSaveRequestDto memberSaveRequestDto){
        memberService.signUp(memberSaveRequestDto);
    }

    @PostMapping("/sign-in")
    public JwtToken signIn(@RequestBody LoginRequest loginRequest){
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        JwtToken jwtToken = memberService.signIn(email, password);
        log.info("Login email = {}", email);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return jwtToken;
    }

    @PostMapping("/test")
    public String test(){
        //현재 멤버 UserName(식별자 - email 반환)
        return SecurityUtil.getCurrentUsername();
    }


}
