package com.juliomesquita.coreapi.common.controller.command.interfaces;

public interface FindAllCommandAdapter<R, C> {
    C toFindAllCommand(R findRequest);
}
