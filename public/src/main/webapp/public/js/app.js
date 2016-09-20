var phonecatApp = angular.module('phonecatApp', [ 'ngRoute', 'controllers', 'clubModule', 'competitionModule' ]);

phonecatApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/clubs/:clubsId', {
		templateUrl : 'public/partials/club.html',
		controller : 'ClubCtrl'
	}).when('/competation/:competitionId/table', {
		templateUrl : 'public/partials/competition.html',
		controller : 'CompetitionCtrl'
	});
} ]);