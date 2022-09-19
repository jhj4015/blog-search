package com.jhj.blogsearch.application.search;

import com.jhj.blogsearch.api.dto.SearchDTO;
import com.jhj.blogsearch.application.search.client.KakaoFeignClient;
import com.jhj.blogsearch.application.search.client.NaverFeignClient;
import com.jhj.blogsearch.application.search.client.dto.KakaoResponseDTO;
import com.jhj.blogsearch.application.search.client.dto.NaverResponseDTO;
import com.jhj.blogsearch.application.search.model.SearchResponsePage;
import com.jhj.blogsearch.application.search.model.mapper.KakaoResponseMapper;
import com.jhj.blogsearch.application.search.model.mapper.NaverResponseMapper;
import com.jhj.blogsearch.application.search.model.mapper.SearchResponseMapper;
import com.jhj.blogsearch.application.trend.TrendKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogSearchService {

    private final KakaoFeignClient kakaoFeignClient;
    private final NaverFeignClient naverFeignClient;
    private final TrendKeywordService trendKeywordService;

    public SearchResponsePage searchBlog(SearchDTO.Req request) {
        SearchDTO.Res searchResultDTO = searchKakaoBlog(request);
        trendKeywordService.updateCountByKeyword(request.getQuery());

        return SearchResponsePage.builder()
                .apiName(searchResultDTO.getApiName())
                .apiSort(request.getSort())
                .isEnd(searchResultDTO.isEnd())
                .content(searchResultDTO.getDocuments())
                .pageNumber(searchResultDTO.getPageableCount())
                .pageSize(request.getPageSize())
                .total(searchResultDTO.getTotalCount())
                .build();
    }

    SearchDTO.Res searchKakaoBlog(SearchDTO.Req request) {
        KakaoResponseDTO responseDTO = kakaoFeignClient.getBlogResult(request);
        SearchResponseMapper<KakaoResponseDTO> searchResponseMapper = new KakaoResponseMapper();
        return searchResponseMapper.mapper(responseDTO);
    }

    SearchDTO.Res searchNaverBlog(SearchDTO.Req request) {
        NaverResponseDTO responseDTO = naverFeignClient.getBlogResult(request);
        SearchResponseMapper<NaverResponseDTO> searchResponseMapper = new NaverResponseMapper();
        return searchResponseMapper.mapper(responseDTO);
    }
}
