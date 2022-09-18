package com.jhj.blogsearch.application.search.mapper;

import com.jhj.blogsearch.api.dto.SearchResultDTO;

public interface SearchResultMapper<T> {
    SearchResultDTO mapper(T responseDTO);
}
