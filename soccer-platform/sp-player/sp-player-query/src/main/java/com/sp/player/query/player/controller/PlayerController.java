package com.sp.player.query.player.controller;

import com.sp.player.query.player.domain.PlayerQuery;
import com.sp.player.query.player.infrastructure.PlayerQueryMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lejsz on 2017. 04. 21..
 */

@RequestMapping("/players/")
@RestController
public class PlayerController {

    @Autowired
    private PlayerQueryMongoRepository playerQueryMongoRepository;

    @RequestMapping(value = "/{clubId}", method = RequestMethod.GET)
    public ResponseEntity<Resources<PlayerQuery>> clubPlayers(@PathVariable String clubId) {
        Resources<PlayerQuery> resources = new Resources(playerQueryMongoRepository.findByClubId(clubId));
        return ResponseEntity.ok(resources);
    }

}
