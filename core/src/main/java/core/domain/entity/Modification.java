package core.domain.entity;

import javax.validation.constraints.Size;

import org.neo4j.ogm.annotation.Relationship;

public class Modification extends BaseNodeEntity {

	@Relationship(type = "AUTHOR")
	private Member author;

	@Size(max = 250)
	private String comment;

	public Member getAuthor() {
		return author;
	}

	public void setAuthor(Member author) {
		this.author = author;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
