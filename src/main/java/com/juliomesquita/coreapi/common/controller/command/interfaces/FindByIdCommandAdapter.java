package com.juliomesquita.coreapi.common.controller.command.interfaces;

public interface FindByIdCommandAdapter<I,C> {
    C toFindByIdCommand(I id);
}
