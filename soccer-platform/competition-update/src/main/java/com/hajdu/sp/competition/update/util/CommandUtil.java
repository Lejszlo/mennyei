package com.hajdu.sp.competition.update.util;

import com.hajdu.sp.competition.update.command.competition.CompetitionCommand;
import io.eventuate.AggregateRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CommandUtil {

    public static  <T, A extends AggregateRepository> CompletableFuture[] convert(A aggregateRepository, String id, List<T> commands) {
        return commands.stream().map(command -> aggregateRepository
                .update(id, (CompetitionCommand) command))
                .toArray(CompletableFuture[]::new);
    }

}
