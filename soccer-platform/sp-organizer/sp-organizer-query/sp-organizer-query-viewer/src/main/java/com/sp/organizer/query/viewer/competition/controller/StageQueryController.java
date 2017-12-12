package com.sp.organizer.query.viewer.competition.controller;

import com.sp.organizer.query.viewer.competition.resource.table.TableQueryResource;
import com.sp.organizer.query.viewer.competition.service.StageQueryService;
import com.sp.organizer.query.viewer.competition.service.TableQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sp.organizer.query.viewer.club.resource.ClubQueryResource;

import java.util.Collection;

import static org.springframework.http.ResponseEntity.*;

@RequestMapping("/api/competition/{competitionId}")
@RestController
public class StageQueryController {

	private final StageQueryService stageQueryService;

	private final TableQueryService tableQueryService;

    @Autowired
    public StageQueryController(StageQueryService stageQueryService, TableQueryService tableQueryService) {
        this.stageQueryService = stageQueryService;
        this.tableQueryService = tableQueryService;
    }

    @RequestMapping(value = "{stageIndex}/clubs", method = RequestMethod.GET)
	public ResponseEntity<Collection<ClubQueryResource>> getClubs(@PathVariable("competitionId") String competitionId, @PathVariable("stageIndex") int stageIndex) {
    	return ok(stageQueryService.getClubs(competitionId, stageIndex));
	}

	@RequestMapping(value = "/{stageIndex}/tables", method = RequestMethod.GET)
	public ResponseEntity<TableQueryResource> getTable(@PathVariable("competitionId") String competitionId, @PathVariable("stageIndex") int stageIndex) {
        return ok(tableQueryService.getTable(competitionId, stageIndex));
    }

}
