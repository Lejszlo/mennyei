package com.mennyei.core.team.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mennyei.core.team.domain.Team;

@Repository
public interface TeamMemoryDao extends JpaRepository<Team, Long> {


}
