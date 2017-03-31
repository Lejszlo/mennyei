var app = angular.module('app', [ 'ngRoute', 'playerModule', 'competitionModule', 'navigationModule', 'matchModule', 'clubModule', 'spring-data-rest' ]);

app.config([ '$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
	
	// use the HTML5 History API
	$locationProvider.html5Mode(true);
	
	$routeProvider.when('/players', {
		templateUrl : 'static/public/partials/player/players.html',
		controller : 'playersCtrl',
		activetab: 'players'
	}).when('/tables', {
		templateUrl : 'static/public/partials/competition/tables.html',
		controller : 'tableCtrl',
		activetab: 'table'
	}).when('/matches', {
		templateUrl : 'static/public/partials/match/matches.html',
		controller : 'matchesCtrl',
		activetab: 'matches'
	}).when('/clubs/:clubsId', {
		templateUrl : 'static/public/partials/club/club.html',
		controller : 'clubCtrl',
		activetab: 'club'
	}).when('/matches/:matchId', {
		templateUrl : 'static/public/partials/match/details.html',
		controller : 'matchDetailsCtrl',
		activetab: 'matches'
	});
} ]);

