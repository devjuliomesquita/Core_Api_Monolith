package com.juliomesquita.coreapi.common.controller.command.interfaces;

public interface CreateCommandAdapter<R, C> {
    C toCreateCommand(R createRequest);
}
