package com.jhj.blogsearch.application.search.model.mapper;

import com.jhj.blogsearch.api.dto.SearchDTO;
import com.jhj.blogsearch.application.search.feign.dto.KakaoDTO;
import java.util.List;
import java.util.stream.Collectors;

public class KakaoSearchMapper implements SearchMapper<KakaoDTO> {

    @Override
    public SearchDTO.Res mapper(KakaoDTO kakaoDTO) {
        return SearchDTO.Res.builder()
                .totalCount(kakaoDTO.getMeta().getTotalCount())
                .pageableCount(kakaoDTO.getMeta().getPageableCount())
                .apiName("KAKAO-BLOG")
                .isEnd(kakaoDTO.getMeta().isEnd())
                .documents(getDocuments(kakaoDTO))
                .build();
    }

    private List<SearchDTO.Res.Document> getDocuments(KakaoDTO kakaoDTO) {
        return kakaoDTO.getDocuments().stream().map(doc -> SearchDTO.Res.Document.builder()
                .title(doc.getTitle())
                .contents(doc.getContents())
                .url(doc.getUrl())
                .blogName(doc.getBlogName())
                .thumbNail(doc.getThumbNail())
                .dateTime(doc.getDateTime()).build()).collect(Collectors.toList());
    }
}
