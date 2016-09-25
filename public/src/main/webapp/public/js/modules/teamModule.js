var teamModule = angular.module('teamModule', []);

teamModule.controller('playersCtrl', function($scope) {
	$scope.players = [ {
		name : "Kis Pista",
		age : 21,
		position : "Vedo",
		leg : "Jobb",
		number : 10,
		mennyeiIndex : 66
	}, {
		name : "Kleinheisler Laszlo",
		age : 22,
		position : "Kapus",
		leg : "Jobb",
		number : 1,
		mennyeiIndex : 34
	}, {
		name : "Gera Zoltan",
		age : 23,
		position : "Csatar",
		leg : "Bal",
		number : 9,
		mennyeiIndex : 99
	}, {
		name : "Nagy Adam",
		age : 24,
		position : "Kozeppalya",
		leg : "Jobb/Bal",
		number : 8,
		mennyeiIndex : 75
	} ]
});