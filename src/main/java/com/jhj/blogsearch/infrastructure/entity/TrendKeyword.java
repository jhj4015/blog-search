package com.jhj.blogsearch.infrastructure.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TrendKeyword {

    @Id
    private String keyword;
    private Long count;
    private LocalDate createdAt;

    public static TrendKeyword of(String keyword, Long count) {
        return new TrendKeyword(keyword, count, LocalDate.now());
    }
}
