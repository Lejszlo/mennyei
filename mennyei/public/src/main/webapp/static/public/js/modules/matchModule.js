var matchModule = angular.module('matchModule', []);

matchModule.controller('nextMatchesCtrl', function ($scope, $http, SpringDataRestAdapter) {
    
	var club = $http.get('/club/vamosoroszikse');

	SpringDataRestAdapter.process(club).then(function (processedResponse) {
		$scope.club = processedResponse;
		$scope.detailesLink = processedResponse._links.detailes;
	});
	
	SpringDataRestAdapter.process(club, 'matches').then(function (processedResponse) {
		$scope.matches = processedResponse.matches._embeddedItems;
	});
	
});

matchModule.controller('matchDetailsCtrl', function ($scope, $http, $routeParams, selectedClub) {
	
	var club = $http.get('/matches/:' + $routeParams.matchId).then(function (processedResponse) {
		$scope.match = processedResponse;
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
