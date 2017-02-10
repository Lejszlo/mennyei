var matchModule = angular.module('matchModule', []);

matchModule.controller('nextMatchesCtrl', function ($scope, $http, SpringDataRestAdapter) {
    
	var club = $http.get('/club/vamosoroszikse');
	
	SpringDataRestAdapter.process(club, 'matches').then(function (processedResponse) {
		$scope.matches = processedResponse.matches._embeddedItems;
	});
	
});

matchModule.controller('matchDetailsCtrl', function ($scope, $http, $routeParams, selectedClub) {
	
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
