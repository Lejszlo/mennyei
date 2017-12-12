package command.competition;

import lombok.*;
import value.competition.season.Stage;

import java.util.List;
import java.util.Set;

@Value
@Builder
@AllArgsConstructor
public class AddStageCommand extends CompetitionCommand {

    @NonNull
    private Stage stage;
}
