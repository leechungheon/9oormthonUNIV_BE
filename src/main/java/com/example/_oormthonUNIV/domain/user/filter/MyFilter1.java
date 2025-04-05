package com.example._oormthonUNIV.domain.user.filter;


import jakarta.servlet.*;

import java.io.IOException;

public class MyFilter1 implements Filter  {
    // 필터의 기능을 구현하는 메서드들 (예: doFilter) 등을 오버라이드하여 사용
    // 예를 들어, 요청을 가로채거나 응답을 수정하는 등의 작업을 수행할 수 있음
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("필터1");
        chain.doFilter(request, response); // 다음 필터 또는 서블릿으로 요청 전달
    }
    // 필터의 초기화 및 종료 메서드도 필요에 따라 오버라이드 가능
}
