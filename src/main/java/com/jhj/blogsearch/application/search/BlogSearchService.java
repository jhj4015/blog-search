package com.jhj.blogsearch.application.search;

import com.jhj.blogsearch.api.dto.SearchRequestDTO;
import com.jhj.blogsearch.api.dto.SearchResultDTO;
import com.jhj.blogsearch.application.search.client.KakaoFeignClient;
import com.jhj.blogsearch.application.search.client.NaverFeignClient;
import com.jhj.blogsearch.application.search.client.dto.KakaoResponseDTO;
import com.jhj.blogsearch.application.search.client.dto.NaverResponseDTO;
import com.jhj.blogsearch.application.search.mapper.KakaoResultMapper;
import com.jhj.blogsearch.application.search.mapper.NaverResultMapper;
import com.jhj.blogsearch.application.search.mapper.SearchResultMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogSearchService {

    private final KakaoFeignClient kakaoFeignClient;
    private final NaverFeignClient naverFeignClient;

    public Page<SearchResultDTO> searchBlog(SearchRequestDTO request, Pageable pageable) {
        SearchResultDTO searchResultDTO = searchKakaoBlog(request, pageable.getPageNumber(),
                pageable.getPageSize());

        return new PageImpl(searchResultDTO.getDocuments(),
                pageable,
                searchResultDTO.getTotalCount());
    }

    private SearchResultDTO searchKakaoBlog(SearchRequestDTO request, int pageNumber, int pageSize) {
        KakaoResponseDTO responseDTO = kakaoFeignClient.getBlogResult(request, pageNumber,
                pageSize);
        SearchResultMapper<KakaoResponseDTO> searchResultMapper = new KakaoResultMapper();
        return searchResultMapper.mapper(responseDTO);
    }

    public SearchResultDTO searchNaverBlog(SearchRequestDTO request, int pageNumber, int pageSize) {
        NaverResponseDTO responseDTO = naverFeignClient.getBlogResult(request, pageNumber,
                pageSize);
        SearchResultMapper<NaverResponseDTO> searchResultMapper = new NaverResultMapper();
        return searchResultMapper.mapper(responseDTO);
    }


}
