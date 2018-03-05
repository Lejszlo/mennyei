export interface Club {
    fullName: string;
    id: string;
    name: string;
    urlName: string;
}

export interface Stage {
    _links : any;
    name: string;
    index: number;
    stageRuleSet: StageRuleSet;
}

export interface StageRuleSet {

}

export interface CompetitionResource {
    competitionInfo : CompetitionInfo;
    _links : any;
}

export interface CompetitionInfo {
}

export interface TableResource {
    rows: Row[];
}

export interface TurnResource {
    index: number;
    interval: string;
    _links : any;
}

export interface MatchResource {
    homeClubName: string;
    awayClubName: string;
    homeGoalAmount: number;
    awayGoalAmount: number;
    matchDate: string;
    _links : any;
}

export interface Row {
    club: Club;
    concerdGoals: number;
    draw: number;
    lose: number;
    playedMatches: number;
    point: number;
    scoredGoals: number;
    win: number;
}