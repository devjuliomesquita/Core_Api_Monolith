package com.juliomesquita.coreapi.common.controller.command;

import com.juliomesquita.coreapi.common.controller.command.interfaces.CreateCommandAdapter;
import com.juliomesquita.coreapi.common.controller.command.interfaces.DeleteCommandAdapter;
import com.juliomesquita.coreapi.common.controller.command.interfaces.FindByIdCommandAdapter;
import com.juliomesquita.coreapi.common.controller.command.interfaces.UpdateCommandAdapter;
import com.juliomesquita.coreapi.common.controller.command.transport.AbstractUpdateCommand;
import com.juliomesquita.coreapi.common.controller.command.transport.AbstractCreateCommand;
import com.juliomesquita.coreapi.common.controller.command.transport.AbstractDeleteCommand;
import com.juliomesquita.coreapi.common.controller.command.transport.AbstractFindByIdCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractCrudCommandAdapter<
        ID, E,
        CR, CC extends AbstractCreateCommand<E>,
        UR, UC extends AbstractUpdateCommand<ID, E>,
        DC extends AbstractDeleteCommand<ID>,
        CI extends AbstractFindByIdCommand<ID, E>
        > implements
        CreateCommandAdapter<CR, CC>,
        UpdateCommandAdapter<ID, UR, UC>,
        DeleteCommandAdapter<ID, DC>,
        FindByIdCommandAdapter<ID, CI> {

    @Override
    public CC toCreateCommand(CR createRequest) {
        return null;
    }

    @Override
    public DC toDeleteCommand(ID id) {
        return null;
    }

    @Override
    public CI toFindByIdCommand(ID id) {
        return null;
    }

    @Override
    public UC toUpdateCommand(ID id, UR updateRequest) {
        return null;
    }
}
