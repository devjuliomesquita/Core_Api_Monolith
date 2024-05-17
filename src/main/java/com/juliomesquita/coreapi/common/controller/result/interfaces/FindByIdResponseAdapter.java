package com.juliomesquita.coreapi.common.controller.result.interfaces;

public interface FindByIdResponseAdapter<IA, IR> {
    IA toFindByIdResponse(IR result);
}
