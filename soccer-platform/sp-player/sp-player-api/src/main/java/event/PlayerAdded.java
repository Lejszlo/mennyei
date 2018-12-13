package event;

import lombok.AllArgsConstructor;
import lombok.Value;
import value.Player;

@Value
@AllArgsConstructor
public class PlayerAdded implements PlayerEvent {

    Player player;

//    private String aggregateId;
}
