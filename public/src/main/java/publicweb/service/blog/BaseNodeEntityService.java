package publicweb.service.blog;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.neo4j.helpers.collection.IteratorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Service;

import core.domain.entity.BaseNodeEntity;
import core.service.exceptions.ColudNotSavedException;

@Service
public abstract class BaseNodeEntityService<T extends BaseNodeEntity> {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	private GraphRepository<T> repository;

	/**
	 * Set that repository which belong to the actual entity, this will be
	 * called after construct.
	 * 
	 * Call: {@link #setRepository(GraphRepository)}
	 * 
	 */
	@PostConstruct
	protected abstract void initEntityRepository();

	public List<T> getAll() {
		Iterable<T> blogPosts = repository.findAll();
		return IteratorUtil.asList(blogPosts);
	}

	public Optional<T> get(Long id) {
		return Optional.ofNullable(repository.findOne(id));
	}

	public Optional<T> save(T post) {
		T save = null;
		try {
			save = saveEntity(post);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return Optional.ofNullable(save);
	}

	private T saveEntity(T post) throws ColudNotSavedException {
		T saved = repository.save(post);
		if (saved == null) {
			throw new ColudNotSavedException(post);
		}
		return saved;
	}

	public void deleteAll() {
		repository.deleteAll();
	}

	public GraphRepository<T> getRepository() {
		assert (repository == null);
		return repository;
	}

	protected void setRepository(GraphRepository<T> repository) {
		this.repository = repository;
	}

}
