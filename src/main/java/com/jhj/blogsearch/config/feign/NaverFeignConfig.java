package com.jhj.blogsearch.config.feign;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class NaverFeignConfig {

    @Bean
    public RequestInterceptor naverRequestInterceptor(
            @Value("${api.naver.client.id}") String NAVER_CLIENT_ID,
            @Value("${api.naver.client.secret}") String NAVER_CLIENT_SECRET) {

        return requestTemplate -> {
            requestTemplate.header("X-Naver-Client-Id", NAVER_CLIENT_ID);
            requestTemplate.header("X-Naver-Client-Secret", NAVER_CLIENT_SECRET);
        };
    }

}
