package publicweb.service.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.domain.entity.Post;
import core.repository.PostRepository;

@Service
public class PostService extends BaseNodeEntityService<Post> {

	@Autowired
	private PostRepository postRepository;

	@Override
	protected void initEntityRepository() {
		setRepository(postRepository);
	}

}
