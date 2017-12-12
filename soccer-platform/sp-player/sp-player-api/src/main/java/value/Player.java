package value;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Player {
	
	private String name;
	
	private Integer number;

	private String birthday;
	
	private String nationality;

}
