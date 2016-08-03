var phonecatApp = angular.module('phonecatApp', [ 'ngRoute', 'controllers' ]);

phonecatApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/clubs/:clubsId', {
		templateUrl : 'public/partials/club.html',
		controller : 'ClubCtrl'
	});
} ]);