package com.jhj.blogsearch.application.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import com.jhj.blogsearch.api.dto.SearchDTO;
import com.jhj.blogsearch.application.search.feign.KakaoFeignClient;
import com.jhj.blogsearch.application.search.feign.NaverFeignClient;
import com.jhj.blogsearch.application.search.feign.dto.KakaoDTO;
import com.jhj.blogsearch.application.search.feign.dto.NaverDTO;
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
    private final SearchDTO.Req searchReqDTO = SearchDTO.Req.builder()
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
        KakaoDTO kakaoDTO = dummyDataReader.readKakaoDummyResponse();
        given(kakaoFeignClient.getBlogResult(searchReqDTO)).willReturn(kakaoDTO);

        // when
        SearchDTO.Res searchResDTO = blogSearchService.searchKakaoBlog(searchReqDTO);

        // then
        assertEquals(22837, searchResDTO.getTotalCount());
        assertEquals(794, searchResDTO.getPageableCount());
        assertEquals("랄라라", searchResDTO.getDocuments().get(1).getBlogName());
    }


    @Test
    void testNaverBlogSearchResult() {
        // given
        NaverDTO naverDTO = dummyDataReader.readNaverDummyResponse();
        given(naverFeignClient.getBlogResult(searchReqDTO)).willReturn(naverDTO);

        // when
        SearchDTO.Res searchResDTO = blogSearchService.searchNaverBlog(searchReqDTO);

        // then
        assertEquals(24645, searchResDTO.getTotalCount());
        assertEquals(1, searchResDTO.getPageableCount());
        assertEquals("코딩일기", searchResDTO.getDocuments().get(3).getBlogName());
    }

}