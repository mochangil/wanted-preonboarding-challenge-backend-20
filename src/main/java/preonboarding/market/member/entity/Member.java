package preonboarding.market.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import preonboarding.market.common.entity.BaseEntity;
import preonboarding.market.member.dto.reqeust.MemberSaveRequestDto;
import preonboarding.market.product.entity.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Member extends BaseEntity implements UserDetails {
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

    public static Member from(MemberSaveRequestDto memberSaveRequestDto, String password, List<String> roles){
        return Member.builder()
                .email(memberSaveRequestDto.email())
                .nickname(memberSaveRequestDto.nickname())
                .password(password)
                .roles(roles)
                .build();
    }

    //1:N 매핑으로 테이블 만들기 (ElementCollection)
    @ElementCollection(fetch=FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }

    //getUsername()은 계정의 고유한 값이다. username 대신 email로 대체한다.
    @Override
    public String getUsername(){
        return email;
    }


}
