package com.hajdu.sp.competition.update.exceptions;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Collection;

@Value
@Builder
public class ExceptionValue<VALUE> {
    @Singular
    Collection<VALUE> values;

}
