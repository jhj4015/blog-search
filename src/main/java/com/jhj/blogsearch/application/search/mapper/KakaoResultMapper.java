package com.jhj.blogsearch.application.search.mapper;

import com.jhj.blogsearch.api.dto.SearchResultDTO;
import com.jhj.blogsearch.application.search.client.dto.KakaoResponseDTO;
import java.util.List;
import java.util.stream.Collectors;

public class KakaoResultMapper implements SearchResultMapper<KakaoResponseDTO> {

    @Override
    public SearchResultDTO mapper(KakaoResponseDTO responseDTO) {
        return SearchResultDTO.builder()
                .totalCount(responseDTO.getMeta().getTotalCount())
                .pageableCount(responseDTO.getMeta().getPageableCount())
                .apiName("KAKAO-BLOG")
                .documents(getDocuments(responseDTO))
                .build();
    }

    private List<SearchResultDTO.Document> getDocuments(KakaoResponseDTO responseDTO) {
        return responseDTO.getDocuments().stream().map(doc -> SearchResultDTO.Document.builder()
                .title(doc.getTitle())
                .contents(doc.getContents())
                .url(doc.getUrl())
                .blogName(doc.getBlogName())
                .thumbNail(doc.getThumbNail())
                .dateTime(doc.getDateTime()).build()).collect(Collectors.toList());
    }
}
