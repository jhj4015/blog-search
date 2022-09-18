package com.jhj.blogsearch.infrastructure.repository;

import com.jhj.blogsearch.infrastructure.entity.TrendKeyword;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrendKeywordRepository extends JpaRepository<TrendKeyword, String> {

    List<TrendKeyword> findTop10TrendKeywordByOrderByCountDesc();
}
