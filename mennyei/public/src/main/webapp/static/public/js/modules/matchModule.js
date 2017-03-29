var matchModule = angular.module('matchModule', []);

matchModule.controller('matchesCtrl', function ($scope, $http, $location, SpringDataRestAdapter, selectedClub, selectedMatch) {
	SpringDataRestAdapter.process(selectedClub, 'matches').then(function (processedResponse) {
		$scope.club = processedResponse;
		$scope.matches = processedResponse.matches._embeddedItems;
	});
	
	$scope.selectMatch = function(match) {
		selectedMatch.selectMatch(match);
		$location.path('/matches/1');
	}
	
});

matchModule.controller('matchDetailsCtrl', function ($scope, $http, $routeParams, SpringDataRestAdapter, selectedClub, selectedMatch) {
	SpringDataRestAdapter.process(selectedMatch.getSelectedMatch(), 'detailes').then(function (processedResponse) {
		$scope.match = processedResponse;
		$scope.homeStarters = processedResponse.detailes.homeStarters;
		$scope.homeSubstitutions = processedResponse.detailes.homeSubstitution;
		$scope.awayStarters = processedResponse.detailes.awayStarters;
		$scope.awaySubstitutions = processedResponse.detailes.awaySubstitution;
	});
	
	SpringDataRestAdapter.process(selectedClub).then(function (processedResponse) {
		$scope.club = processedResponse;
	});
	
	const pictorUrl ="../../../../static/images/picto/";
	$scope.getEventImage = function(event) {
		if(event.matchEventType === "GOAL") {
			return pictorUrl + "goal.png"
		}
		if(event.matchEventType === "RED_CARD") {
			return pictorUrl + "red_card.png"
		}
		if(event.matchEventType === "YELLOW_CARD") {
			return pictorUrl + "yellow_card.png"
		}
	}
	
	$scope.getTeamNames = function() {
		var match = selectedMatch.getSelectedMatch();
		var result = {};
		if(match.atHome) {
			result.homeClubName = $scope.club.name;
			result.awayClubName = match.opponentClubName;
			return result; 
		}
		result.awayClubName = $scope.club.name;
		result.homeClubName = match.opponentClubName;
		return result; 
	}
	
})

matchModule.service('selectedMatch', function ($http) {
	var selectedMatch;
	
	return {
		selectMatch : function(match) {
			this.selectedMatch = match;
		},

		getSelectedMatch : function() {
			return this.selectedMatch;
		}
	}
});

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
