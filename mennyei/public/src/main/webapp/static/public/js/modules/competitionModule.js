var competitionModule = angular.module('competitionModule', []);

competitionModule.controller('tableCtrl', function($scope, $http) {
	$scope.calculatePositionHighlights = function(index) {
		if(index < $scope.promotion) {
			return "success";
		}
		if(index >= ($scope.clubs.length - $scope.relegation)) {
			return "danger";
		}
	}
	
	$http.get("/competition/alma/alma/table").success(function(data) {
		$scope.clubs = data.rows;
		$scope.competitionName = data.competitionName;
		$scope.relegation = data.relegation;
		$scope.promotion = data.promotion;
	});
});