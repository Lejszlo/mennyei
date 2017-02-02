var teamModule = angular.module('teamModule', []);

teamModule.controller('playersCtrl', function($scope, Restangular) {
	
	var basePlayers = Restangular.one('clubs', "00000159ffee1694-0000000000e00001").get().then(function (playersList) {
		$scope.players = playersList.getList("players");
		$scope.selectedPlayer = playersList[0];
	}, function(playerList) {
		console.println("");
	}, function(playerList) {
		console.println("");
	});
	
});