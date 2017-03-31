var competitionModule = angular.module('competitionModule', []);

competitionModule.controller('tableCtrl', function($scope, $http, selectedClub, SpringDataRestAdapter) {
	var selectedCompetition;
	
	SpringDataRestAdapter.process(selectedClub, 'competitions').then(function (processedResponse) {
		$scope.competitions = processedResponse.competitions._embeddedItems;
		selectedCompetition = $scope.competitions[0];
		
		SpringDataRestAdapter.process(selectedCompetition, 'tables').then(function (processedResponse) {
			$scope.tableRows = processedResponse.tables._embeddedItems[0].rows;
		});
	});
	
	$scope.calculatePositionHighlights = function(index) {
		if(index < selectedCompetition.promotion) {
			return "success";
		}
		if(index >= (selectedCompetition.clubs.length - selectedCompetition.relegation)) {
			return "danger";
		}
	}
	
})

competitionModule.directive('stageTable', function() {
	return {
		restrict: 'E',
		templateUrl: '/static/public/partials/competition/directives/stage-table.html'
	}
});