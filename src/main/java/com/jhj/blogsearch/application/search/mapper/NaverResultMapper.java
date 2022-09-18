package com.jhj.blogsearch.application.search.mapper;

import com.jhj.blogsearch.api.dto.SearchResultDTO;
import com.jhj.blogsearch.application.search.client.dto.NaverResponseDTO;
import java.util.List;
import java.util.stream.Collectors;

public class NaverResultMapper implements SearchResultMapper<NaverResponseDTO> {

    @Override
    public SearchResultDTO mapper(NaverResponseDTO responseDTO) {
        return SearchResultDTO.builder()
                .totalCount(responseDTO.getTotal())
                .pageableCount(responseDTO.getStart())
                .apiName("NAVER-BLOG")
                .documents(getDocuments(responseDTO))
                .build();
    }

    private List<SearchResultDTO.Document> getDocuments(NaverResponseDTO responseDTO) {
        return responseDTO.getItems().stream().map(item -> SearchResultDTO.Document.builder()
                .title(item.getTitle())
                .contents(item.getDescription())
                .url(item.getUrl())
                .blogName(item.getBloggerName())
                .thumbNail(item.getLink())
                .dateTime(item.getPostDate()).build()).collect(Collectors.toList());
    }
}
