package com.jhj.blogsearch.api.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor(staticName = "of")
public class SearchResultDTO {

    private final int totalCount;
    private final int pageableCount;
    private final String apiName;
    private final boolean isEnd;

    private final List<Document> documents;

    @Getter
    @Builder
    public static class Document {

        private String title;
        private String contents;
        private String url;
        private String blogName;
        private String thumbNail;
        private String dateTime;
    }

}