package com.jhj.blogsearch.api.dto;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortType {
    ACCURACY("accuracy"),
    RECENCY("recency");

    private final String kakaoSortType;

    public static SortType getKakaoSortType(String type) {
        return Arrays.stream(SortType.values())
                .filter(s -> s.kakaoSortType.equals(type))
                .findFirst()
                .orElse(SortType.ACCURACY);
    }
}