package com.jhj.blogsearch.application.trend;

import static java.util.stream.Collectors.toList;

import com.jhj.blogsearch.api.dto.TrendKeywordResultDTO;
import com.jhj.blogsearch.infrastructure.entity.TrendKeyword;
import com.jhj.blogsearch.infrastructure.repository.RedisTrendKeywordRepository;
import com.jhj.blogsearch.infrastructure.repository.TrendKeywordRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrendKeywordService {

    private final RedisTrendKeywordRepository redisTrendKeywordRepository;
    private final TrendKeywordRepository trendKeywordRepository;

    public void updateCountByKeyword(String keyword) {
        redisTrendKeywordRepository.updateCountByKeyword(keyword);
    }

    public List<TrendKeywordResultDTO> getTop10TrendKeywordsLookAside() {
        try {
            return redisTrendKeywordRepository.findTop10ByOrderByCountDesc();
        } catch (Exception e) {
            log.error("Failed, get the redis cache trend keyword, {}", e.getMessage());

        }
        return trendKeywordRepository.findTop10TrendKeywordByOrderByCountDesc().stream()
                .map(t -> TrendKeywordResultDTO.of(t.getKeyword(), t.getCount())).collect(toList());
    }

    // @todo, redis 서버가 죽는 것을 대비해 10분 간격으로 DB 저장 스케줄링
    public void backupTrendKeywordAndCount() {
        List<TrendKeywordResultDTO> redisTop10Keywords = redisTrendKeywordRepository.findTop10ByOrderByCountDesc();
        List<TrendKeyword> list = redisTop10Keywords.stream()
                .map(t -> TrendKeyword.of(t.getKeyword(), t.getCount()))
                .collect(toList());
        trendKeywordRepository.saveAll(list);
    }
}
