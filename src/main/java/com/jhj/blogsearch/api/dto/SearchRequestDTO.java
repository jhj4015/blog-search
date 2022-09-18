package com.jhj.blogsearch.api.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDTO {

    @NonNull
    private String query;
    private String sort;
}
