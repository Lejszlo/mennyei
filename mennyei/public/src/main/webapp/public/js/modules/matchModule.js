var matchModule = angular.module('matchModule', []);

matchModule.controller('nextMatchesCtrl', function ($scope, $http) {
    
	$http.get("/clubs/as/as/as/matches").success(function(data) {
		$scope.matches = data;
	});
	
});

matchModule.controller('matchDetailsCtrl', function ($scope, $http, $routeParams) {
	
	$http.get("/competition/as/as/"+ $routeParams.turnId + "/" + $routeParams.clubId).success(function(data) {
		$scope.matchQuery = data.matchQuery;
	});
	
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
