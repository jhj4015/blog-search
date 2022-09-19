package com.jhj.blogsearch.application.search.model.mapper;

import com.jhj.blogsearch.api.dto.SearchDTO;
import com.jhj.blogsearch.application.search.feign.dto.NaverDTO;
import java.util.List;
import java.util.stream.Collectors;

public class NaverSearchMapper implements SearchMapper<NaverDTO> {

    @Override
    public SearchDTO.Res mapper(NaverDTO naverDTO) {
        return SearchDTO.Res.builder()
                .totalCount(naverDTO.getTotal())
                .pageableCount(naverDTO.getStart())
                .apiName("NAVER-BLOG")
                .isEnd(false)
                .documents(getDocuments(naverDTO))
                .build();
    }

    private List<SearchDTO.Res.Document> getDocuments(NaverDTO naverDTO) {
        return naverDTO.getItems().stream().map(item -> SearchDTO.Res.Document.builder()
                .title(item.getTitle())
                .contents(item.getDescription())
                .url(item.getUrl())
                .blogName(item.getBloggerName())
                .thumbNail(item.getLink())
                .dateTime(item.getPostDate()).build()).collect(Collectors.toList());
    }
}
