package publicweb.service.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.domain.entity.Modification;
import core.repository.ModificationRepository;

@Service
public class ModificationService extends BaseNodeEntityService<Modification> {

	@Autowired
	private ModificationRepository modificationRepository;

	@Override
	protected void initEntityRepository() {
		setRepository(modificationRepository);
	}

}
