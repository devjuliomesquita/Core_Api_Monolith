package com.juliomesquita.coreapi.common.controller.command.interfaces;

public interface DeleteCommandAdapter<I, C> {
    C toDeleteCommand(I id);
}
