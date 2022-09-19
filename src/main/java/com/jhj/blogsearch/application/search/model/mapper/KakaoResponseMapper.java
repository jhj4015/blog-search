package com.jhj.blogsearch.application.search.model.mapper;

import com.jhj.blogsearch.api.dto.SearchDTO;
import com.jhj.blogsearch.application.search.client.dto.KakaoResponseDTO;
import java.util.List;
import java.util.stream.Collectors;

public class KakaoResponseMapper implements SearchResponseMapper<KakaoResponseDTO> {

    @Override
    public SearchDTO.Res mapper(KakaoResponseDTO responseDTO) {
        return SearchDTO.Res.builder()
                .totalCount(responseDTO.getMeta().getTotalCount())
                .pageableCount(responseDTO.getMeta().getPageableCount())
                .apiName("KAKAO-BLOG")
                .isEnd(responseDTO.getMeta().isEnd())
                .documents(getDocuments(responseDTO))
                .build();
    }

    private List<SearchDTO.Res.Document> getDocuments(KakaoResponseDTO responseDTO) {
        return responseDTO.getDocuments().stream().map(doc -> SearchDTO.Res.Document.builder()
                .title(doc.getTitle())
                .contents(doc.getContents())
                .url(doc.getUrl())
                .blogName(doc.getBlogName())
                .thumbNail(doc.getThumbNail())
                .dateTime(doc.getDateTime()).build()).collect(Collectors.toList());
    }
}
