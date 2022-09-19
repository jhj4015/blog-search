package com.jhj.blogsearch.infrastructure.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TrendKeyword {

    @Id
    private String keyword;
    private Long count;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public static TrendKeyword of(String keyword, Long count) {
        return new TrendKeyword(keyword, count, LocalDateTime.now());
    }
}