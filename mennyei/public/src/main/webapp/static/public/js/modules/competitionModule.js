var competitionModule = angular.module('competitionModule', []);

competitionModule.controller('tableCtrl', function($scope, $http, selectedClub) {
	$scope.calculatePositionHighlights = function(index) {
		if(index < $scope.promotion) {
			return "success";
		}
		if(index >= ($scope.clubs.length - $scope.relegation)) {
			return "danger";
		}
	}
	
	SpringDataRestAdapter.process(selectedClub, 'matches').then(function (processedResponse) {
		$scope.club = processedResponse;
		$scope.matches = processedResponse.matches._embeddedItems;
	});
});