package hhplus.concert.support.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper(response);
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper(request);

        // req logging
        String url = httpServletRequest.getRequestURI();
        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("request url:{}, request body {}", url, reqContent);

        // 다음 필터 요청 전달
        filterChain.doFilter(httpServletRequest, httpServletResponse);

        //rep logging
        int httpStatus = httpServletResponse.getStatus();
        String repContent = new String(httpServletResponse.getContentAsByteArray());
        httpServletResponse.copyBodyToResponse();
        log.info("response status: {}. response: {}", httpStatus, repContent);
    }

    @Override
    protected  boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] excludesPath = {"/swagger-ui"};
        String path = request.getRequestURI();
        return Arrays.stream(excludesPath).anyMatch(path::startsWith);
    }
}
