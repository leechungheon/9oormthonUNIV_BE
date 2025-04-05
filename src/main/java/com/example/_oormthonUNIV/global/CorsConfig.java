package com.example._oormthonUNIV.global;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * CORS(Cross-Origin Resource Sharing) 설정 클래스.
 * 프론트엔드와 백엔드가 다른 도메인에 있을 경우, CORS 정책으로 인해 요청이 차단되지 않도록 허용 설정을 해주는 필터를 등록한다.
 */
@Configuration
public class CorsConfig {

    // CorsFilter Bean 등록 메서드
    public CorsFilter corsFilter() {
        // URL 패턴에 따른 CORS 정책을 등록할 수 있도록 해주는 소스 객체 생성
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // CORS 설정 객체 생성
        CorsConfiguration config = new CorsConfiguration();

        // 자격 증명(쿠키, 인증 헤더 등)을 포함한 요청 허용
        config.setAllowCredentials(true);

        // 모든 도메인에서의 요청 허용 ("*" 사용 시 credentials는 false로 해야 하지만, setAllowCredentials(true)와 함께 사용할 경우 Spring Boot 2.4+부터는 문제가 생길 수 있음)
        config.addAllowedOrigin("*");

        // 모든 헤더 허용
        config.addAllowedHeader("*");

        // 모든 HTTP 메서드 허용 (GET, POST, PUT, DELETE 등)
        config.addAllowedMethod("*");

        // 위에서 설정한 CORS 정책을 "/api/**" 경로에 적용
        source.registerCorsConfiguration("/api/**", config);

        // 설정한 CORS 정책을 기반으로 필터를 생성하여 반환
        return new CorsFilter(source);
    }
}
