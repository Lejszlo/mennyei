var competitionModule = angular.module('competitionModule', []);

competitionModule.controller('tableCtrl', function($scope) {
	$scope.calculatePositionHighlights = function(index) {
		if(index == 0) {
			return "success";
		}
		if(index >= 13) {
			return "danger";
		}
	}
	
	$scope.clubs = [ {
		name : "Vamosoroszi KSE",
		match : 2,
		win : 2,
		draw : 0,
		lose: 0,
		goalScored : 11,
		goalConcerd : 5
	}, {
		name : "Tisztaberek SE",
		match : 2,
		win : 2,
		draw : 0,
		lose: 0,
		goalScored : 11,
		goalConcerd : 5
	}, {
		name : "Tarpa SC",
		match : 2,
		win : 2,
		draw : 0,
		lose: 0,
		goalScored : 11,
		goalConcerd : 5
	}, {
		name : "Csenger FC",
		match : 2,
		win : 2,
		draw : 0,
		lose: 0,
		goalScored : 11,
		goalConcerd : 5
	}, {
		name : "Nyircsaholy USE",
		match : 2,
		win : 2,
		draw : 0,
		lose: 0,
		goalScored : 11,
		goalConcerd : 5
	}, {
		name : "Szatmarcseke KSE",
		match : 2,
		win : 2,
		draw : 0,
		lose: 0,
		goalScored : 11,
		goalConcerd : 5
	}, {
		name : "Kolcse SE",
		match : 2,
		win : 2,
		draw : 0,
		lose: 0,
		goalScored : 11,
		goalConcerd : 5
	}, {
		name : "Merk-Vallaj NSE",
		match : 2,
		win : 2,
		draw : 0,
		lose: 0,
		goalScored : 11,
		goalConcerd : 5
	}, {
		name : "Tiszakorod SE",
		match : 2,
		win : 2,
		draw : 0,
		lose: 0,
		goalScored : 11,
		goalConcerd : 5
	}, {
		name : "Tyukod FC",
		match : 2,
		win : 2,
		draw : 0,
		lose: 0,
		goalScored : 11,
		goalConcerd : 5
	}, {
		name : "Nyirmeggyes Sportklub",
		match : 2,
		win : 2,
		draw : 0,
		lose: 0,
		goalScored : 11,
		goalConcerd : 5
	}, {
		name : "Nabrad SE",
		match : 2,
		win : 2,
		draw : 0,
		lose: 0,
		goalScored : 11,
		goalConcerd : 5
	}, {
		name : "Milota SE",
		match : 2,
		win : 2,
		draw : 0,
		lose: 0,
		goalScored : 11,
		goalConcerd : 5
	}, {
		name : "Csengersimaert KHE",
		match : 2,
		win : 2,
		draw : 0,
		lose: 0,
		goalScored : 11,
		goalConcerd : 5
	}, {
		name : "Nagydobosi LSE",
		match : 2,
		win : 2,
		draw : 0,
		lose: 0,
		goalScored : 11,
		goalConcerd : 5
	} ]
});