export interface Club {
    fullName: string;
    id: string;
    name: string;
    urlName: string;
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