package hhplus.concert.support.interceptor;

import hhplus.concert.domain.queue.QueueService;
import hhplus.concert.support.exception.CustomException;
import hhplus.concert.support.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthTokenInterceptor implements HandlerInterceptor {

    private QueueService queueService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Token");

        if(token == null || token.isEmpty()) {
            log.warn("Missing Token in request header");
            throw new CustomException(ErrorCode.NOT_READ_TOKEN);
        }

        try {

            queueService.validateToken(token);
            return HandlerInterceptor.super.preHandle(request, response, handler);

        }catch (CustomException e) {
            log.error("Invalid Token : {}", token, e);
            throw e;
        }
    }
}
