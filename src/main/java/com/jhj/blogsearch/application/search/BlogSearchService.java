package com.jhj.blogsearch.application.search;

import com.jhj.blogsearch.api.dto.SearchRequestDTO;
import com.jhj.blogsearch.api.dto.SearchResultDTO;
import com.jhj.blogsearch.application.search.client.KakaoFeignClient;
import com.jhj.blogsearch.application.search.client.NaverFeignClient;
import com.jhj.blogsearch.application.search.client.dto.KakaoResponseDTO;
import com.jhj.blogsearch.application.search.client.dto.NaverResponseDTO;
import com.jhj.blogsearch.application.search.model.SearchResultPage;
import com.jhj.blogsearch.application.search.model.mapper.KakaoResultMapper;
import com.jhj.blogsearch.application.search.model.mapper.NaverResultMapper;
import com.jhj.blogsearch.application.search.model.mapper.SearchResultMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogSearchService {

    private final KakaoFeignClient kakaoFeignClient;
    private final NaverFeignClient naverFeignClient;

    public SearchResultPage searchBlog(SearchRequestDTO request) {
        SearchResultDTO searchResultDTO = searchKakaoBlog(request);

        return SearchResultPage.builder()
                .apiName(searchResultDTO.getApiName())
                .apiSort(request.getSort())
                .isEnd(searchResultDTO.isEnd())
                .content(searchResultDTO.getDocuments())
                .pageNumber(searchResultDTO.getPageableCount())
                .pageSize(request.getPageSize())
                .total(searchResultDTO.getTotalCount())
                .build();
    }

    SearchResultDTO searchKakaoBlog(SearchRequestDTO request) {
        KakaoResponseDTO responseDTO = kakaoFeignClient.getBlogResult(request);
        SearchResultMapper<KakaoResponseDTO> searchResultMapper = new KakaoResultMapper();
        return searchResultMapper.mapper(responseDTO);
    }

    SearchResultDTO searchNaverBlog(SearchRequestDTO request) {
        NaverResponseDTO responseDTO = naverFeignClient.getBlogResult(request);
        SearchResultMapper<NaverResponseDTO> searchResultMapper = new NaverResultMapper();
        return searchResultMapper.mapper(responseDTO);
    }
}
