package com.hajdu.sp.competition.update.util;

public interface Invariant<AGGREGATE, COMMAND> {

    void check(AGGREGATE aggregate, COMMAND data) throws Exception;

}
