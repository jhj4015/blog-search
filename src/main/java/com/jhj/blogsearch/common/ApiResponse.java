package com.jhj.blogsearch.common;

import lombok.Getter;
import lombok.Builder;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ApiResponse<T> {

    private int code;
    private T data;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime serverDatetime;

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .code(HttpStatus.OK.value())
                .data(data)
                .serverDatetime(LocalDateTime.now())
                .build();
    }
}
