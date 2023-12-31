package com.amam.amamjwt.jwt;

import com.amam.amamjwt.exception.CustomException;
import com.amam.amamjwt.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class JwtDecoder {

    @Value("${jwt.token.secretKey}")
    private String secretKey;

    @Value("${jwt.redirect-url}")
    private String url;

    public Long verifyJWT(HttpServletRequest request) throws UnsupportedEncodingException {
        log.info("Secret Key is Empty : {}", secretKey.isEmpty());

        if (secretKey.isEmpty()) {
            throw new CustomException(ErrorCode.SECRET_KEY_EMPTY);
        }

        String check = Optional.ofNullable(request.getHeader("Authorization"))
                .orElse(null);

        log.info("Access Key is Empty : {}", check);

        if (check == null) {
            throw new CustomException(ErrorCode.TOKEN_NOT_FOUND);
        }

        String authorization = request.getHeader("Authorization").substring(7);
        log.info("Access Key : {}", authorization);

        Map<String, Object> claimMap;

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey) // Set SignKey
                    .parseClaimsJws(authorization) // 파싱 및 검증, 실패 시 에러
                    .getBody();

            claimMap = claims;

//            Date expiration = claims.get("exp", Date.class);
//            log.info("exp : {}", expiration);
            Long userId = Long.valueOf(claimMap.get("userId").toString());

            log.info("userId : {}", userId);

            return userId;
        }
        catch (ExpiredJwtException e) { // 토큰이 만료되었을 경우
            log.info("EXPIRE TOKEN =====");
            throw new CustomException(ErrorCode.AUTH_EXPIRED_TOKEN);
        }
        catch (Exception e) { // 그외 에러났을 경우
            log.info("# ERROR TOKEN ====== ", e);
            throw new CustomException(ErrorCode.AUTH_WRONG_TOKEN);
        }
    }
}