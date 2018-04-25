export interface Club {
    fullName: string;
    id: string;
    name: string;
    urlName: string;
}

export interface Stage {
    _links : any;
    name: string;
    uuiD: number;
    stageRuleSet: StageRuleSet;
}

export interface StageRuleSet {
    numberOfTeams: number;
    pointsForWin: number;
    pointsForDraw: number;
    pointsForLose: number;
    numberOfMatches: number;
    subsNamed: number;
    subsUsed: number;
    relegation: number;
    promotion: number;
    yellowCardLimit: number;
    yellowCardsBan: number;
    redCardsBan: number;
    oneHalfMinutes: number;
    hasOverTime: number;
    numberOfHalfs: number;
    oneHalfOverTimeMinutes: number;
    hasPenalties: boolean;
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
    homeClubDocumentResource: Club;
    awayClubDocumentResource: Club;
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