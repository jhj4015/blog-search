package com.jhj.blogsearch.application.search;

import com.jhj.blogsearch.api.dto.SearchDTO;
import com.jhj.blogsearch.application.search.feign.KakaoFeignClient;
import com.jhj.blogsearch.application.search.feign.NaverFeignClient;
import com.jhj.blogsearch.application.search.feign.dto.KakaoDTO;
import com.jhj.blogsearch.application.search.feign.dto.NaverDTO;
import com.jhj.blogsearch.application.search.model.SearchPage;
import com.jhj.blogsearch.application.search.model.mapper.KakaoSearchMapper;
import com.jhj.blogsearch.application.search.model.mapper.NaverSearchMapper;
import com.jhj.blogsearch.application.search.model.mapper.SearchMapper;
import com.jhj.blogsearch.application.trend.TrendKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogSearchService {

    private final KakaoFeignClient kakaoFeignClient;
    private final NaverFeignClient naverFeignClient;
    private final TrendKeywordService trendKeywordService;

    public SearchPage searchBlog(SearchDTO.Req request) {
        final SearchDTO.Res searchResDTO = searchKakaoBlog(request);
        trendKeywordService.updateCountByKeyword(request.getQuery());

        return SearchPage.builder()
                .apiName(searchResDTO.getApiName())
                .apiSort(request.getSort())
                .isEnd(searchResDTO.isEnd())
                .content(searchResDTO.getDocuments())
                .pageNumber(searchResDTO.getPageableCount())
                .pageSize(request.getPageSize())
                .total(searchResDTO.getTotalCount())
                .build();
    }

    SearchDTO.Res searchKakaoBlog(SearchDTO.Req request) {
        KakaoDTO responseDTO = kakaoFeignClient.getBlogResult(request);
        SearchMapper<KakaoDTO> searchMapper = new KakaoSearchMapper();
        return searchMapper.mapper(responseDTO);
    }

    SearchDTO.Res searchNaverBlog(SearchDTO.Req request) {
        NaverDTO responseDTO = naverFeignClient.getBlogResult(request);
        SearchMapper<NaverDTO> searchMapper = new NaverSearchMapper();
        return searchMapper.mapper(responseDTO);
    }
}
