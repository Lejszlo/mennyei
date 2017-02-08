var app = angular.module('app', [ 'ngRoute', 'teamModule', 'competitionModule', 'navigationModule', 'matchModule', 'clubModule', 'spring-data-rest' ]);

app.config([ '$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
	
	// use the HTML5 History API
	$locationProvider.html5Mode(true);
	
	$routeProvider.when('/clubs/players', {
		templateUrl : 'static/public/partials/players.html',
		controller : 'playersCtrl',
		activetab: 'players'
	}).when('/competation/:competitionId/table', {
		templateUrl : 'static/public/partials/table.html',
		controller : 'tableCtrl',
		activetab: 'table'
	}).when('/competation/:competitionId/turn/:turnNumber', {
		templateUrl : 'static/public/partials/matches.html',
		controller : 'nextMatchesCtrl',
		activetab: 'matches'
	}).when('/clubs/:clubsId', {
		templateUrl : 'static/public/partials/club.html',
		controller : 'clubDetailesCtrl',
		activetab: 'club'
	}).when('/competition/:competitionId/stage/:stage/turn/:turnId/club/:clubId', {
		templateUrl : 'static/public/partials/match/details.html',
		controller : 'matchDetailsCtrl',
		activetab: 'matches'
	});
} ]);

