package com.juliomesquita.coreapi.common.controller.command.transport;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class AbstractFilter {
    private final String word;
    private final Pageable pageable;

    protected AbstractFilter(String word, Pageable pageable) {
        this.word = word;
        this.pageable = pageable;
    }
}
