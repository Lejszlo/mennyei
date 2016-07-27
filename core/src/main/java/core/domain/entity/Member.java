package core.domain.entity;

import org.neo4j.ogm.annotation.Property;

public class Member extends BaseNodeEntity {

	@Property(name = "name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
