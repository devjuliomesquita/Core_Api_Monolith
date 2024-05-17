package com.juliomesquita.coreapi.common.controller.command.interfaces;

public interface UpdateCommandAdapter<I, R, C> {
    C toUpdateCommand(I id, R updateRequest);
}
