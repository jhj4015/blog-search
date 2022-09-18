package com.jhj.blogsearch.application.search.model.mapper;

import com.jhj.blogsearch.api.dto.SearchResultDTO;

public interface SearchResultMapper<T> {
    SearchResultDTO mapper(T responseDTO);
}
