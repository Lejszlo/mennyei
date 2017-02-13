var clubModule = angular.module('clubModule', []);

clubModule.controller('clubDetailesCtrl', function($scope) {
    $scope.club = {
        fullName: "Vámosoroszi KSE",
        shortName: "VKSE"
    }
});

clubModule.service('selectedClub', function ($http) {
	return $http.get('/club/vamosoroszikse');
});