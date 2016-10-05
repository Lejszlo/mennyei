var phonecatApp = angular.module('phonecatApp', [ 'ngRoute', 'teamModule', 'competitionModule', 'navigationModule', 'matchModule', 'clubModule' ]);

phonecatApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/clubs/:clubsId/players', {
		templateUrl : 'public/partials/players.html',
		controller : 'playersCtrl',
		activetab: 'players'
	}).when('/competation/:competitionId/table', {
		templateUrl : 'public/partials/table.html',
		controller : 'tableCtrl',
		activetab: 'table'
	}).when('/competation/:competitionId/turn/:turnNumber', {
		templateUrl : 'public/partials/matches.html',
		controller : 'nextMatchesCtrl',
		activetab: 'matches'
	}).when('/clubs/:clubsId', {
		templateUrl : 'public/partials/club.html',
		controller : 'clubDetailesCtrl',
		activetab: 'club'
	}).when('/competation/:competitionId/matches/:matchId', {
		templateUrl : 'public/partials/match/details.html',
		controller : 'matchDetailsCtrl',
		activetab: 'matches'
	});
} ]);