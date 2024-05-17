package com.juliomesquita.coreapi.common.controller.command.transport;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class AbstractFindAllCommand<E> {
    private final AbstractFilter filter;
    private final E entity;

    protected AbstractFindAllCommand(AbstractFilter filter, E entity) {
        this.filter = filter;
        this.entity = entity;
    }
}
