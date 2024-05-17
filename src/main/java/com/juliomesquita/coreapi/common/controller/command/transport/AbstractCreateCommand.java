package com.juliomesquita.coreapi.common.controller.command.transport;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class AbstractCreateCommand<E> {
    private final E entity;

    protected AbstractCreateCommand(E entity) {
        this.entity = entity;
    }
}
