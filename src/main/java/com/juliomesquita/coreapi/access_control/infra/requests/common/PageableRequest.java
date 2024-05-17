package com.juliomesquita.coreapi.access_control.infra.requests.common;

import com.juliomesquita.coreapi.access_control.domain.enums.SortType;

public record PageableRequest(
        String word,
        SortType sortType,
        Integer page,
        Integer perPage
) {
}
