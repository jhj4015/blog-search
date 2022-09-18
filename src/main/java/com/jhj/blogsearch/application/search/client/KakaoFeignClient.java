package com.jhj.blogsearch.application.search.client;

import com.jhj.blogsearch.api.dto.SearchRequestDTO;
import com.jhj.blogsearch.application.search.client.dto.KakaoResponseDTO;
import com.jhj.blogsearch.config.KakaoFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaoClient", url = "https://dapi.kakao.com", configuration = KakaoFeignConfig.class)
public interface KakaoFeignClient {

    @GetMapping(path = "/v2/search/blog", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    KakaoResponseDTO getBlogResult(
            @SpringQueryMap SearchRequestDTO searchRequestDTO, @RequestParam(value = "1") int page,
            @RequestParam(value = "10") int size);
}
