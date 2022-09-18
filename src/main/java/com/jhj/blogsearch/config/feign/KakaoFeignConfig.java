package com.jhj.blogsearch.config.feign;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class KakaoFeignConfig {

    @Bean
    public RequestInterceptor kakaoRequestInterceptor(
            @Value("${api.kakao.token}") String KAKAO_API_KEY) {
        return requestTemplate -> requestTemplate.header("Authorization", KAKAO_API_KEY);
    }
}
