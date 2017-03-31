var playerModule = angular.module('playerModule', []);

playerModule.controller('playersCtrl', function($scope, SpringDataRestAdapter, $http) {
	
	var club = $http.get('/club/vamosoroszikse');
	
	SpringDataRestAdapter.process(club, 'players').then(function (processedResponse) {
		$scope.players = processedResponse.players._embeddedItems;
	});
	
	$scope.getPlayerTotalGoals = function(player) {
		return getPlayerStatisticPropertySum(player, 'scoredGoalAmount');
	}
	
	$scope.getPlayerTotalMatches = function(player) {
		return getPlayerStatisticPropertySum(player, 'totalMatch');
	}
	
	$scope.getPlayerTotalYellowCards = function(player) {
		return getPlayerStatisticPropertySum(player, 'yellowCardAmount');
	}
	
	$scope.getPlayerTotalRedCards = function(player) {
		return getPlayerStatisticPropertySum(player, 'redCardAmount');
	}
	
	function getPlayerStatisticPropertySum(player, propertyName) {
		var sum = 0;
		for(var name in player.playerMatchStatisticDatas) {
		    var datas = player.playerMatchStatisticDatas[name];
		    sum += datas[propertyName];
		}
		return sum;
	}
	
});