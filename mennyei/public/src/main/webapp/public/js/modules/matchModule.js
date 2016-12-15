var matchModule = angular.module('matchModule', []);

matchModule.controller('nextMatchesCtrl', function ($scope, $http) {
    
	$http.get("/clubs/as/as/as/matches").success(function(data) {
		$scope.matches = data;
//		$scope.matches = [{
//	        turn: 1,
//	        homeId: 111,
//	        home: "Vámosoroszi",
//	        homeLogo: "static/images/crest/szszbm/vamosoroszi_big.jpg",
//	        homeScore: 2,
//	        homeResult: "win",
//	        awayId: 333,
//	        away: "Kölcse",
//	        awayLogo: "static/images/crest/szszbm/kolcse_big.jpg",
//	        awayScore: 0,
//	        time: "17:00",
//	        date: "2016.10.21 (Szo)",
//	        awayResult: "lose",
//	        state: "played"
//	    }]

//	    $scope.resultClasses = function(match) {
//	        if(match.homeId == 111) {
//	            return getClass(match.homeResult);
//	        } else {
//	            return getClass(match.awayResult);
//	        }
//	    }
	});
	
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
