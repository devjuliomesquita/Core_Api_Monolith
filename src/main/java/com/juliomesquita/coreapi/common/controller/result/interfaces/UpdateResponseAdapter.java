package com.juliomesquita.coreapi.common.controller.result.interfaces;

public interface UpdateResponseAdapter<UC, UR> {
    UC toUpdateResponse(UR result);
}
