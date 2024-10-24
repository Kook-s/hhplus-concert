package hhplus.concert.support.config;

import hhplus.concert.support.interceptor.AuthTokenInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthTokenInterceptor authTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authTokenInterceptor)
                .addPathPatterns("/api/v1/concert/**")
                .addPathPatterns("/api/v1/payment/**")
                .addPathPatterns("/api/v1/reservation/**");

    }

}
