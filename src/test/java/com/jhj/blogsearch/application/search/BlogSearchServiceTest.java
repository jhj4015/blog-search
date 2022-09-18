package com.jhj.blogsearch.application.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import com.jhj.blogsearch.api.dto.SearchRequestDTO;
import com.jhj.blogsearch.api.dto.SearchResultDTO;
import com.jhj.blogsearch.application.search.client.KakaoFeignClient;
import com.jhj.blogsearch.application.search.client.NaverFeignClient;
import com.jhj.blogsearch.application.search.client.dto.KakaoResponseDTO;
import com.jhj.blogsearch.application.search.client.dto.NaverResponseDTO;
import com.jhj.blogsearch.support.DummyDataReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

@MockitoSettings
class BlogSearchServiceTest {

    @InjectMocks
    private BlogSearchService blogSearchService;

    @Mock
    private KakaoFeignClient kakaoFeignClient;

    @Mock
    private NaverFeignClient naverFeignClient;

    private DummyDataReader dummyDataReader;
    private final SearchRequestDTO searchRequestDTO
                                    = SearchRequestDTO.builder()
                                    .query("hello-kakao")
                                    .sort("ACCURACY")
                                    .pageNumber(1)
                                    .pageSize(5)
                                    .build();


    @BeforeEach
    void setUp() {
        dummyDataReader = new DummyDataReader();
    }

    @Test
    void testKakaoBlogSearchResult() {
        // given
        KakaoResponseDTO kakaoResponseDTO = dummyDataReader.readKakaoDummyResponse();
        given(kakaoFeignClient.getBlogResult(searchRequestDTO)).willReturn(kakaoResponseDTO);

        // when
        SearchResultDTO searchResultDTO = blogSearchService.searchKakaoBlog(searchRequestDTO);

        // then
        assertEquals(22837, searchResultDTO.getTotalCount());
        assertEquals(794, searchResultDTO.getPageableCount());
        assertEquals("랄라라", searchResultDTO.getDocuments().get(1).getBlogName());
    }


    @Test
    void testNaverBlogSearchResult() {
        // given
        NaverResponseDTO naverResponseDTO = dummyDataReader.readNaverDummyResponse();
        given(naverFeignClient.getBlogResult(searchRequestDTO)).willReturn(naverResponseDTO);

        // when
        SearchResultDTO searchResultDTO = blogSearchService.searchNaverBlog(searchRequestDTO);

        // then
        assertEquals(24645, searchResultDTO.getTotalCount());
        assertEquals(1, searchResultDTO.getPageableCount());
        assertEquals("코딩일기", searchResultDTO.getDocuments().get(3).getBlogName());
    }

}