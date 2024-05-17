package com.juliomesquita.coreapi.common.controller.command.transport;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class AbstractUpdateCommand<I, E> {
    private final I id;
    private final E entity;

    protected AbstractUpdateCommand(I id, E entity) {
        this.id = id;
        this.entity = entity;
    }
}
