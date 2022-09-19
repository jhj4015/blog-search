package com.jhj.blogsearch.api;

import com.jhj.blogsearch.api.dto.SearchRequestDTO;
import com.jhj.blogsearch.application.search.BlogSearchService;
import com.jhj.blogsearch.application.search.model.SearchResponsePage;
import com.jhj.blogsearch.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class BlogSearchController {

    private final BlogSearchService blogSearchService;

    @Operation(summary = "Blog Search API", description = "Blog Search API with OPEN API")
    @GetMapping("/blog")
    public ApiResponse<SearchResponsePage> searchBlog(@RequestParam(name = "query") String query,
            @RequestParam(name = "sort") String sort,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "30") int size) {

        if (query.isBlank()) {
            throw new IllegalArgumentException("검색어를 입력해주세요");
        }

        SearchResponsePage searchResponsePage = blogSearchService.searchBlog(SearchRequestDTO.of(query, sort, page, size));
        return ApiResponse.success(searchResponsePage);
    }

}
