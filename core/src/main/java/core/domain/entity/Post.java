package core.domain.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

public class Post extends BaseNodeEntity {

	@Property(name = "title")
	private String title;

	@Property(name = "lead")
	private String lead;

	@Property(name = "content")
	private String content;

	@Relationship(type = "AUTHOR")
	private Member author;

	@Relationship(type = "MODIFIED_BY")
	private Set<Modification> modifications = new HashSet<Modification>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLead() {
		return lead;
	}

	public void setLead(String lead) {
		this.lead = lead;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Member getAuthor() {
		return author;
	}

	public void setAuthor(Member author) {
		this.author = author;
	}

	public Set<Modification> getModifications() {
		return modifications;
	}

	public void setModifications(Set<Modification> modifications) {
		this.modifications = modifications;
	}

}
