package preonboarding.market.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


}
