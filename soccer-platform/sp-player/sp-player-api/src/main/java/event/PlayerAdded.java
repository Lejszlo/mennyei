package event;

import lombok.AllArgsConstructor;
import lombok.Value;
import value.Player;

/**
 * Created by lejsz on 2016. 11. 22..
 */
@Value
@AllArgsConstructor
public class PlayerAdded implements PlayerEvent {

    private Player player;

//    private String aggregateId;
}
