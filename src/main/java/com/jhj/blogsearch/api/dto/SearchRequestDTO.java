package com.jhj.blogsearch.api.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor(staticName = "of")
public class SearchRequestDTO {

    @NotNull
    private String query;
    private String sort;
    @Min(1)
    private int pageNumber;
    @Min(1)
    private int pageSize;
}
