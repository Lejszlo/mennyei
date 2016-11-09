package com.mennyei.core.player.inrfastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mennyei.core.player.domain.Player;

public interface PlayerMemoryDao extends JpaRepository<Player, Long> {

}
