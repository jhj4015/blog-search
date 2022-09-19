package com.jhj.blogsearch.application.search.model.mapper;

import com.jhj.blogsearch.api.dto.SearchDTO;

public interface SearchResponseMapper<T> {

    SearchDTO.Res mapper(T responseDTO);
}
