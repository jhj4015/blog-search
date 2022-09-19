package com.jhj.blogsearch.application.search.model.mapper;

import com.jhj.blogsearch.api.dto.SearchResultDTO;

public interface SearchResponseMapper<T> {
    SearchResultDTO mapper(T responseDTO);
}
