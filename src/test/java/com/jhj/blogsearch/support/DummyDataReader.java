package com.jhj.blogsearch.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhj.blogsearch.application.search.client.dto.KakaoResponseDTO;
import com.jhj.blogsearch.application.search.client.dto.NaverResponseDTO;
import java.io.InputStream;


public class DummyDataReader {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public KakaoResponseDTO readKakaoDummyResponse() {
        try (InputStream stream
                = KakaoResponseDTO.class.getResourceAsStream("/data/kakao-dummy-response.json")) {
            return objectMapper.readValue(stream, KakaoResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("파일을 읽지 못했습니다.", e);
        }
    }

    public NaverResponseDTO readNaverDummyResponse() {
        try (InputStream stream
                = NaverResponseDTO.class.getResourceAsStream("/data/naver-dummy-response.json")) {
            return objectMapper.readValue(stream, NaverResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("파일을 읽지 못했습니다.", e);
        }
    }
}