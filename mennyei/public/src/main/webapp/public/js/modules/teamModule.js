var teamModule = angular.module('teamModule', []);

teamModule.controller('playersCtrl', function($scope, Restangular) {
	
	var basePlayers = Restangular.one("clubs").customGET("search/findByUrlName", "vamosoroszikse").customGET("players").then(function (playersList) {
		$scope.players = playersList._embedded.playerQueries;
		$scope.selectedPlayer = $scope.players[0];
	});
	
	$scope.selectPlayer = function(player) {
		$scope.selectedPlayer = player;
		console.log($scope.selectedPlayer.name)
	}
	
});