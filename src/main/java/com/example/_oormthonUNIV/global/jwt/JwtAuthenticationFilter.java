package com.example._oormthonUNIV.global.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    // JWT 인증 필터는 UsernamePasswordAuthenticationFilter를 상속받아 구현합니다.
    // 이 필터는 사용자가 로그인할 때 JWT 토큰을 생성하고, 이를 클라이언트에게 반환하는 역할을 합니다.
    // 또한, JWT 토큰을 검증하여 사용자의 인증 정보를 확인하는 역할도 수행합니다.

    // 필터의 동작 방식은 다음과 같습니다:
    // 1. 사용자가 로그인 요청을 보냅니다.
    // 2. 필터는 요청에서 사용자 이름과 비밀번호를 추출합니다.
    // 3. 사용자 이름과 비밀번호를 사용하여 인증을 수행합니다.
    // 4. 인증이 성공하면 JWT 토큰을 생성하고, 이를 클라이언트에게 반환합니다.
    // 5. 클라이언트는 이후 요청에서 JWT 토큰을 포함하여 서버에 요청을 보냅니다.
    // 6. 서버는 JWT 토큰을 검증하여 사용자의 인증 정보를 확인합니다.
    // 7. 인증이 성공하면 요청을 처리하고, 실패하면 에러를 반환합니다.
    // 이 필터는 스프링 시큐리티의 필터 체인에 등록되어, 인증 요청이 들어올 때마다 자동으로 실행됩니다.
    private final AuthenticationManager authenticationManager;
    // 로그인 시도를 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // 사용자가 입력한 사용자 이름과 비밀번호를 추출합니다.
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        // UsernamePasswordAuthenticationToken 객체를 생성합니다.
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // 인증을 수행합니다.
        return authenticationManager.authenticate(authenticationToken);
    }
}
