var phonecatApp = angular.module('phonecatApp', [ "chart.js", 'ngRoute', 'controllers', 'filters' ]);

phonecatApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/clubs/:clubsId', {
		templateUrl : 'public/partials/club.html',
		controller : 'ClubCtrl'
	});
} ]);