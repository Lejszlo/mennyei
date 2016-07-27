package publicweb.service.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.domain.entity.Member;
import core.repository.MemberRepository;

@Service
public class MemberService extends BaseNodeEntityService<Member> {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	protected void initEntityRepository() {
		setRepository(memberRepository);
	}

}
