package core.domain.entity;

import java.util.Date;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.typeconversion.DateString;

@NodeEntity
public class BaseNodeEntity {

	@GraphId
	private Long id;

	@Property(name = "createdDate")
	@DateString("yyyy.MM.dd HH:mm:ss")
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
