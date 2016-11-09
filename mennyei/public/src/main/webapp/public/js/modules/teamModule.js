var teamModule = angular.module('teamModule', []);

teamModule.controller('playersCtrl', function($scope, $http) {
	
	$http.get("/clubs/2/players").success(function(data) {
		$scope.players = data;
	});
});