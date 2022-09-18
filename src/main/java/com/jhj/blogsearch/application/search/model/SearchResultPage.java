package com.jhj.blogsearch.application.search.model;

import com.jhj.blogsearch.api.dto.SearchResultDTO;
import com.jhj.blogsearch.api.dto.SearchResultDTO.Document;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@Getter
public class SearchResultPage extends PageImpl<SearchResultDTO.Document> {

    private final String apiName;
    private final String apiSort;
    private final boolean isEnd;

    @Builder
    public SearchResultPage(String apiName, String apiSort, boolean isEnd, List<Document> content,
            int pageNumber,
            int pageSize, int total) {
        super(content, PageRequest.of(pageNumber, pageSize), total);
        this.apiName = apiName;
        this.apiSort = apiSort;
        this.isEnd = isEnd;
    }
}
