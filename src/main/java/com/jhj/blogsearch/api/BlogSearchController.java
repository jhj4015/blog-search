package com.jhj.blogsearch.api;

import com.jhj.blogsearch.api.dto.SearchRequestDTO;
import com.jhj.blogsearch.api.dto.SearchResultDTO;
import com.jhj.blogsearch.application.search.BlogSearchService;
import com.jhj.blogsearch.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class BlogSearchController {

    private final BlogSearchService blogSearchService;

    @Operation(summary = "Blog Search API", description = "Blog Search API with OPEN API")
    @GetMapping("/blog")
    public ApiResponse<Page<SearchResultDTO>> searchBlog(@ModelAttribute
            SearchRequestDTO request, @Parameter(hidden = true)@PageableDefault(page = 1) Pageable pageable) {
        Page<SearchResultDTO> searchResults = blogSearchService.searchBlog(request,
                pageable);
        return ApiResponse.success(searchResults);
    }

}
