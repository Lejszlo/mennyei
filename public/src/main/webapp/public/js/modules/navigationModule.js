var navigationModule = angular.module('navigationModule', []);

navigationModule.controller('tabsCtrl', function($scope, $route) {
    $scope.$route = $route;
});