package preonboarding.market.member.dto.reqeust;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import preonboarding.market.member.entity.Member;

public record MemberSaveRequestDto(
        String email,
        String password,
        String nickname
) {public MemberSaveRequestDto(Member member){
    this(member.getEmail(),member.getPassword(),member.getNickname());
}
}
