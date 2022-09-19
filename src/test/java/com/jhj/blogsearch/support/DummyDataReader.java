package com.jhj.blogsearch.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhj.blogsearch.application.search.client.dto.KakaoDTO;
import com.jhj.blogsearch.application.search.client.dto.NaverDTO;
import java.io.InputStream;


public class DummyDataReader {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public KakaoDTO readKakaoDummyResponse() {
        try (InputStream stream
                = KakaoDTO.class.getResourceAsStream("/data/kakao-dummy-response.json")) {
            return objectMapper.readValue(stream, KakaoDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("파일을 읽지 못했습니다.", e);
        }
    }

    public NaverDTO readNaverDummyResponse() {
        try (InputStream stream
                = NaverDTO.class.getResourceAsStream("/data/naver-dummy-response.json")) {
            return objectMapper.readValue(stream, NaverDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("파일을 읽지 못했습니다.", e);
        }
    }
}