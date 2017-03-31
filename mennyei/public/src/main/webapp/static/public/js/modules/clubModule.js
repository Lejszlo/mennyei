var clubModule = angular.module('clubModule', []);

clubModule.controller('clubCtrl', function($scope) {
}).directive('clubInfos', function() {
	return {
		restrict: 'E',
		templateUrl: '/static/public/partials/club/directives/club-infos.html'
	}
});

clubModule.service('selectedClub', function ($http) {
	return $http.get('/club/vamosoroszikse');
});
