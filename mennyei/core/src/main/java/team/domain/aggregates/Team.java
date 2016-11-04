package team.domain.aggregates;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Team {

	private String fullName;
	
	private String shortName;
	
}
