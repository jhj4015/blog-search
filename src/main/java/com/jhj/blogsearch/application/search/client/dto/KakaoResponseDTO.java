package com.jhj.blogsearch.application.search.client.dto;

import java.util.List;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
public class KakaoResponseDTO {

    private Meta meta;
    private List<Document> documents;

    @Getter
    public static class Meta {

        @JsonProperty("total_count")
        private int totalCount;
        @JsonProperty("pageable_count")
        private int pageableCount;
        @JsonProperty("is_end")
        private boolean isEnd;
    }

    @Getter
    public static class Document {

        private String title;
        private String contents;
        private String url;
        @JsonProperty("blogname")
        private String blogName;
        @JsonProperty("thumbnail")
        private String thumbNail;
        @JsonProperty("datetime")
        private String dateTime;
    }
}
