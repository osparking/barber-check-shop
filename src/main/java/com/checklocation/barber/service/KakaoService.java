package com.checklocation.barber.service;

import com.checklocation.barber.dto.KakaoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class KakaoService {

    @Value("${kakao.rest-api-key}")
    private String restApiKey;

    private final RestClient restClient = RestClient.create();

    /**
     * 키워드로 장소 검색
     *
     * @param query  검색어 (필수)
     * @param x      경도 (선택, 중심 좌표)
     * @param y      위도 (선택, 중심 좌표)
     * @param radius 반경 (선택, 미터)
     * @param page   페이지 (선택)
     * @param size   한 페이지 결과 수 (선택, 최대 15)
     * @return 검색 결과
     */
    public KakaoResponse searchByKeyword(String query, String x, String y,
                                         Integer radius, Integer page, Integer size) {
        String url = UriComponentsBuilder
                .fromUriString("https://dapi.kakao.com/v2/local/search/keyword.json")
                .queryParam("query", query)
                .queryParamIfPresent("x", java.util.Optional.ofNullable(x))
                .queryParamIfPresent("y", java.util.Optional.ofNullable(y))
                .queryParamIfPresent("radius", java.util.Optional.ofNullable(radius))
                .queryParamIfPresent("page", java.util.Optional.ofNullable(page))
                .queryParamIfPresent("size", java.util.Optional.ofNullable(size))
                .toUriString();

        return restClient.get()
                .uri(url)
                .header(HttpHeaders.AUTHORIZATION, "KakaoAK " + restApiKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(KakaoResponse.class);
    }
}