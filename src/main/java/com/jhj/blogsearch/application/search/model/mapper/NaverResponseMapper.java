package com.jhj.blogsearch.application.search.model.mapper;

import com.jhj.blogsearch.api.dto.SearchDTO;
import com.jhj.blogsearch.application.search.client.dto.NaverResponseDTO;
import java.util.List;
import java.util.stream.Collectors;

public class NaverResponseMapper implements SearchResponseMapper<NaverResponseDTO> {

    @Override
    public SearchDTO.Res mapper(NaverResponseDTO responseDTO) {
        return SearchDTO.Res.builder()
                .totalCount(responseDTO.getTotal())
                .pageableCount(responseDTO.getStart())
                .apiName("NAVER-BLOG")
                .isEnd(false)       // @ todo, naver api는 다음 페이지 여부 리턴 값이 없음, 개선 필요
                .documents(getDocuments(responseDTO))
                .build();
    }

    private List<SearchDTO.Res.Document> getDocuments(NaverResponseDTO responseDTO) {
        return responseDTO.getItems().stream().map(item -> SearchDTO.Res.Document.builder()
                .title(item.getTitle())
                .contents(item.getDescription())
                .url(item.getUrl())
                .blogName(item.getBloggerName())
                .thumbNail(item.getLink())
                .dateTime(item.getPostDate()).build()).collect(Collectors.toList());
    }
}
