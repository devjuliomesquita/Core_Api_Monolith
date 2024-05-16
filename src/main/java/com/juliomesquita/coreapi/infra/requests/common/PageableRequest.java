package com.juliomesquita.coreapi.infra.requests.common;

import com.juliomesquita.coreapi.domain.enums.SortType;

public record PageableRequest(
        String word,
        SortType sortType,
        Integer page,
        Integer perPage
) {
}
