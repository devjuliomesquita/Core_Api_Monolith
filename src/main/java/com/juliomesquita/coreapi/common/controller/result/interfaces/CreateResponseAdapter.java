package com.juliomesquita.coreapi.common.controller.result.interfaces;

public interface CreateResponseAdapter<CA, CR> {
    CA toCreateResponse(CR result);
}
