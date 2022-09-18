package com.jhj.blogsearch.api;

import com.jhj.blogsearch.api.dto.TrendKeywordResultDTO;
import com.jhj.blogsearch.application.trend.TrendKeywordService;
import com.jhj.blogsearch.common.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trend")
public class TrendKeywordController {

    private final TrendKeywordService trendKeywordService;

    @GetMapping(value = "/rank")
    public ApiResponse<List<TrendKeywordResultDTO>> searchRank() {
        return ApiResponse.success(trendKeywordService.getTop10TrendKeywordsLookAside());
    }
}
