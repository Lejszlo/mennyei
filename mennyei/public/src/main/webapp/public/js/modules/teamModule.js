var teamModule = angular.module('teamModule', []);

teamModule.controller('playersCtrl', function($scope, Restangular) {
	
	var basePlayers = Restangular.all('players').customGET("search/findByClub", {club:"00000159feccb203-0000000000e00001"}).then(function (playersList) {
		$scope.players = playersList._embedded.playerQueries;
		$scope.selectedPlayer = playersList[0];
	}, function(playerList) {
		console.println("");
	}, function(playerList) {
		console.println("");
	});
	
});