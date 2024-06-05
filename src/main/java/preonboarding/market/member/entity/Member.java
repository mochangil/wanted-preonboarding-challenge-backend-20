package preonboarding.market.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import preonboarding.market.common.entity.BaseEntity;
import preonboarding.market.member.dto.reqeust.MemberSaveRequestDto;
import preonboarding.market.product.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Member extends BaseEntity {
    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String nickname;

    @OneToMany(mappedBy = "member")
    private List<Product> products = new ArrayList<>();

    public static Member from(MemberSaveRequestDto memberSaveRequestDto){
        return Member.builder()
                .email(memberSaveRequestDto.email())
                .nickname(memberSaveRequestDto.nickname())
                .password(memberSaveRequestDto.password())
                .build();
    }

}
