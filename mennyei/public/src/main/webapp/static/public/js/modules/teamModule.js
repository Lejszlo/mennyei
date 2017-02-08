var teamModule = angular.module('teamModule', []);

teamModule.controller('playersCtrl', function($scope, SpringDataRestAdapter, $http) {
	
	var club = $http.get('/club/vamosoroszikse');
	
	SpringDataRestAdapter.process(club, 'players').then(function (processedResponse) {
		$scope.players = processedResponse.players._embeddedItems;
		$scope.selectedPlayer = $scope.players[0];
	});
	
	$scope.selectPlayer = function(player) {
		$scope.selectedPlayer = player;
		console.log($scope.selectedPlayer.name)
	}
	
});