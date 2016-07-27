package publicweb.controller;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.domain.entity.Member;
import core.domain.entity.Modification;
import core.domain.entity.Post;
import publicweb.service.blog.MemberService;
import publicweb.service.blog.ModificationService;
import publicweb.service.blog.PostService;

@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private ModificationService modificationService;

	@RequestMapping(value = "/blog/get", method = RequestMethod.GET)
	public List<Post> getAll() {
		return postService.getAll();
	}

	@RequestMapping(value = "/blog/get/{blogPostId}", method = RequestMethod.GET)
	public Post get(@PathVariable Long blogPostId) {
		Optional<Post> optional = postService.get(blogPostId);
		return optional.orElse(new Post());
	}

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public void init() {
		memberService.deleteAll();
		Member member = new Member();
		member.setName("Gipsz Jakap");
		Instant instant = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();
		member.setCreatedDate(Date.from(instant));

		modificationService.deleteAll();
		Modification modification = new Modification();
		modification.setAuthor(member);
		modification.setComment("Lorem ipsum");
		modification.setCreatedDate(Date.from(instant));
		modificationService.save(modification);

		postService.deleteAll();
		Post post = new Post();
		post.setContent("Teszt Content");
		post.setLead("Teszt Lead");
		post.setTitle("Teszt Title");
		post.setCreatedDate(Date.from(instant));
		post.setAuthor(member);
		post.getModifications().add(modification);
		postService.save(post);

	}

}
