package preonboarding.market.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {
    //jwt 인증타입 ("Bearer")
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
