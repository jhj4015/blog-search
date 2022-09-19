package com.jhj.blogsearch.application.search.feign.dto;

import com.jhj.blogsearch.application.search.feign.dto.KakaoDTO.Document;
import com.jhj.blogsearch.application.search.feign.dto.KakaoDTO.Meta;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class SearchTransfer {

    public KakaoDTO naverResToKakaoRes(NaverDTO naverDTO) {
        Meta meta = KakaoDTO.Meta.builder()
                .totalCount(naverDTO.getTotal())
                .pageableCount(naverDTO.getDisplay())
                .isEnd(false)
                .build();

        List<Document> documents = getKakaoDocuments(naverDTO);
        return KakaoDTO.of(meta, documents, true);
    }

    private List<Document> getKakaoDocuments(NaverDTO responseDTO) {
        return responseDTO.getItems().stream().map(item -> Document.builder()
                .title(item.getTitle())
                .contents(item.getDescription())
                .url(item.getUrl())
                .blogName(item.getBloggerName())
                .thumbNail(item.getLink())
                .dateTime(item.getPostDate()).build()).collect(Collectors.toList());
    }

}
