package com.hajdu.sp.competition.update.validation;

public interface Invariant<RESOURCE, VALUE> {

    void check(RESOURCE resource, VALUE data);

}
