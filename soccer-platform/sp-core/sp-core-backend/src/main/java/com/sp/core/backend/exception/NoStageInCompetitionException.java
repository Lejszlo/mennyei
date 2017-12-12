package com.sp.core.backend.exception;

public class NoStageInCompetitionException extends RuntimeException {

    public NoStageInCompetitionException() {
        super("There is no one stage in competition!");
    }
}
