package com.jhj.blogsearch.api.dto;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class SearchDTO {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor(staticName = "of")
    public static class Req {

        @NotNull
        private String query;
        private String sort;
        @Min(1)
        private int pageNumber;
        @Min(30)
        private int pageSize;
    }

    @Getter
    @Builder
    @RequiredArgsConstructor(staticName = "of")
    public static class Res {

        private final int totalCount;
        private final int pageableCount;
        private final String apiName;
        private final boolean isEnd;

        private final List<com.jhj.blogsearch.api.dto.SearchDTO.Res.Document> documents;

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

}