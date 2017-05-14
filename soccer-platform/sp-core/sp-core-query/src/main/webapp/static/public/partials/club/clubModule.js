var clubModule = angular.module('clubModule', []);

clubModule.controller('clubCtrl', function($scope) {
}).directive('clubInfos', function() {
	return {
		restrict: 'E',
		templateUrl: '/static/public/partials/com.sp.organizer.backend.club/directives/com.sp.organizer.backend.club-infos.html'
	}
});

clubModule.service('selectedClub', function ($http) {
	return $http.get('/clubs/vamosoroszikse');
});
