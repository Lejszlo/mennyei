package com.mennyei.core.team.service;

import org.springframework.stereotype.Repository;

import com.mennyei.core.team.domain.Team;

@Repository
public interface TeamRepository {
	Team findById(Long id);
	Team save(Team team);
	Team load(Team team);
}
