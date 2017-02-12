var app = angular.module('app', [ 'ngRoute', 'teamModule', 'competitionModule', 'navigationModule', 'matchModule', 'clubModule', 'spring-data-rest' ]);

app.config([ '$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
	
	// use the HTML5 History API
	$locationProvider.html5Mode(true);
	
	$routeProvider.when('/players', {
		templateUrl : 'static/public/partials/players.html',
		controller : 'playersCtrl',
		activetab: 'players'
	}).when('/tables', {
		templateUrl : 'static/public/partials/table.html',
		controller : 'tableCtrl',
		activetab: 'table'
	}).when('/matches', {
		templateUrl : 'static/public/partials/matches.html',
		controller : 'nextMatchesCtrl',
		activetab: 'matches'
	}).when('/clubs/:clubsId', {
		templateUrl : 'static/public/partials/club.html',
		controller : 'clubDetailesCtrl',
		activetab: 'club'
	}).when('/matches/:matchId', {
		templateUrl : 'static/public/partials/match/details.html',
		controller : 'matchDetailsCtrl',
		activetab: 'matches'
	});
} ]);

