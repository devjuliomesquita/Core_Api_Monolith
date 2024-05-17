package com.juliomesquita.coreapi.common.controller.command.transport;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class AbstractDeleteCommand<I> {
    private final Set<I> ids;

    protected AbstractDeleteCommand(Set<I> ids) {
        this.ids = ids;
    }
}
