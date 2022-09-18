package com.jhj.blogsearch.application.search.client;

import com.jhj.blogsearch.api.dto.SearchRequestDTO;
import com.jhj.blogsearch.application.search.client.dto.NaverResponseDTO;
import com.jhj.blogsearch.config.feign.NaverFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ref ) https://developers.naver.com/docs/serviceapi/search/blog/blog.md#%EB%B8%94%EB%A1%9C%EA%B7%B8
 */

@FeignClient(name = "naverClient", url = "https://openapi.naver.com", configuration = NaverFeignConfig.class)
public interface NaverFeignClient {

    @GetMapping(path = "/v1/search/blog", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    NaverResponseDTO getBlogResult(
            @SpringQueryMap SearchRequestDTO searchRequestDTO);

}
