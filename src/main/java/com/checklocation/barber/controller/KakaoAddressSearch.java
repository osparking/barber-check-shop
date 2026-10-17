package com.checklocation.barber.controller;

import com.checklocation.barber.dto.KakaoResponse;
import com.checklocation.barber.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kakao")
@RequiredArgsConstructor
public class KakaoAddressSearch {

    private final KakaoService kakaoLocalSearchService;

    @GetMapping("/search/keyword")
    public KakaoResponse searchKeyword(
            @RequestParam String query,
            @RequestParam(required = false) String x,
            @RequestParam(required = false) String y,
            @RequestParam(required = false) Integer radius,
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "15") Integer size) {

        return kakaoLocalSearchService.searchByKeyword(query, x, y, radius, page, size);
    }
}