var matchModule = angular.module('matchModule', []);

matchModule.controller('nextMatchesCtrl', function ($scope) {
    $scope.matches = [{
        turn: 1,
        homeId: 111,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: 2,
        homeResult: "win",
        awayId: 333,
        away: "Kölcse",
        awayLogo: "static/images/crest/szszbm/kolcse_big.jpg",
        awayScore: 0,
        time: "17:00",
        date: "2016.10.21 (Szo)",
        awayResult: "lose",
        state: "played"
    },{
        turn: 2,
        homeId: 222,
        home: "Nábrád",
        homeLogo: "static/images/crest/szszbm/nabrad_big.jpg",
        homeScore: 3,
        homeResult: "draw",
        awayId: 111,
        away: "Vámosoroszi",
        awayLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        awayScore: 3,
        awayResult: "draw",
        time: "2016.10.29 (V) 15:00",
        state: "played"
    },{
        turn: 3,
        homeId: 111,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: 0,
        homeResult: "lose",
        awayId: 777,
        away: "Milota",
        awayLogo: "static/images/crest/szszbm/milota_big.jpg",
        awayScore: 2,
        awayResult: "win",
        time: "2016.11.6 Szombat 13:00",
        state: "played"
    },{
        turn: 4,
        homeId: 555,
        home: "Szatmárcseke",
        homeLogo: "static/images/crest/szszbm/szatmarcseke_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Vámosoroszi",
        awayLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "next"
    },{
        turn: 5,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 6,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 7,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 8,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 9,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 10,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 10,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 10,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 10,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 10,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 10,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 10,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 10,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 10,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 10,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 10,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 10,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 10,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    },{
        turn: 10,
        homeId: 999,
        home: "Vámosoroszi",
        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
        homeScore: "",
        awayId: 111,
        away: "Csenger",
        awayLogo: "static/images/crest/szszbm/csenger_big.jpg",
        awayScore: "",
        time: "2016.11.13 Vasárnap 13:00",
        result: "",
        state: "notplayed"
    }]

    $scope.resultClasses = function(match) {
        if(match.homeId == 111) {
            return getClass(match.homeResult);
        } else {
            return getClass(match.awayResult);
        }
    }
});

matchModule.controller('matchDetailsCtrl', function ($scope) {

})

function getClass(result) {
    if(result == "win") {
        return "success";
    }
    if(result == "draw") {
        return "warning";
    }
    if(result == "lose") {
        return "danger";
    }
}
