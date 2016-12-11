var teamModule = angular.module('teamModule', []);

teamModule.controller('playersCtrl', function($scope, $http) {

	$http.get("/clubs/vamosoroszikse/players").success(function(data) {
		$scope.players = data;
		$scope.selectedPlayer =  data[0];
	});
	
});