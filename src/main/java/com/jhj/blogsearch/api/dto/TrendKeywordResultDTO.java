package com.jhj.blogsearch.api.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class TrendKeywordResultDTO {

    private final String keyword;
    private final Long count;
}
