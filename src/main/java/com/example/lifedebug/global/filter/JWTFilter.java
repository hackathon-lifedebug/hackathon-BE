package com.example.lifedebug.global.filter;

import com.example.lifedebug.global.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.lettuce.core.pubsub.PubSubOutput.Type.message;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        System.out.println(">> 요청 URI: " + uri);

        if (uri.startsWith("/auth/login") || uri.startsWith("/auth/signup")) {
            System.out.println(">> 인증 예외 경로: " + uri);
            filterChain.doFilter(request, response);
            return;
        }

        // 요청 헤더에서 Authorization 추출
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.replace("Bearer ", "");

        if (uri.equals("/auth/reissue")) {
            request.setAttribute("refreshToken", token);
        } else {
            request.setAttribute("accessToken", token);
        }

        // JWT 유효성 검사
        try {
            if (!jwtUtil.isValid(token)) {
                setErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 토큰입니다.");
                return;
            }
        } catch (ExpiredJwtException e) {
            setErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "토큰이 만료되었습니다.");
            return;
        } catch (JwtException e) {
            setErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "JWT 처리 중 오류가 발생했습니다.");
            return;
        }

        // 사용자 정보 추출
        String loginId = jwtUtil.getSubject(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);
        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // SecurityContext에 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 다음 필터로 넘기기
        filterChain.doFilter(request, response);
    }

    private void setErrorResponse(HttpServletResponse response, int statusCode, String message) throws IOException {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("status", statusCode);
        errorBody.put("message", message);

        response.setStatus(statusCode);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(errorBody));
    }
}
