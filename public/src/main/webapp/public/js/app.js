var phonecatApp = angular.module('phonecatApp', [ 'ngRoute', 'controllers', 'clubModule', 'competitionModule' ]);

phonecatApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/clubs/:clubsId', {
		templateUrl : 'public/partials/club.html',
		controller : 'ClubCtrl'
	}).when('/clubCompare/:homeClubsId;:awayClubsId', {
		templateUrl : 'public/partials/club.html',
		controller : 'ClubCompareCtrl'
	}).when('/competation/:competitionId', {
		templateUrl : 'public/partials/competition.html',
		controller : 'CompetitionCtrl'
	});
} ]);