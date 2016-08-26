var controllers = angular.module('controllers', []);

controllers.controller('ClubCtrl', function($scope, $routeParams) {
	$scope.categories = [ {
		name : "Hazai",
	}, {
		name : "Idegenbeli",
	} ]

	$scope.labels = [ "Gyozelem", "Dontetlen", "Vereseg" ];
	$scope.data = [ 10, 3, 1 ];
	$scope.crest = "static/images/crest/szszbm/222px-Vámosoroszi_címere.jpg";

	$scope.club = {
		name : "Vamosoroszi Kozsegi Sport Egyesulet",
		foundation : "1965",
		city : "Vamosoroszi",
		phone : "+36 70 123 45 67",
		mail : "vamosoroszi@teszt.hu",
	}

	$scope.competition = {
		name : "Kelet-mo. megye II. felnott",
		state : "Szabolcs Szatmar Bereg megye"
	}

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

	$scope.stuffs = [ {
		name : "Kis Pista",
		age : 21,
		title : "Edzo",
		leg : "Jobb",
		number : 10,
		mennyeiIndex : 66
	}, {
		name : "Kleinheisler Laszlo",
		age : 22,
		title : "Igazgato",
	} ]

	$scope.clubs = [ {
		name : "Vamosoroszi KSE",
		point : 6,
	}, {
		name : "Tisztaberek SE",
		point : 6,
	}, {
		name : "Tarpa SC",
		point : 4,
	}, {
		name : "Csenger FC",
		point : 4,
	}, {
		name : "Nyircsaholy USE",
		point : 4,
	}, {
		name : "Szatmarcseke KSE",
		point : 4,
	}, {
		name : "Csenger FC",
		point : 4,
	}, {
		name : "Csenger FC",
		point : 4,
	}, {
		name : "Csenger FC",
		point : 4,
	}, {
		name : "Csenger FC",
		point : 4,
	}, {
		name : "Csenger FC",
		point : 4,
	}, {
		name : "Csenger FC",
		point : 4,
	}, {
		name : "Csenger FC",
		point : 4,
	} ]

	$scope.nextMatch = {
		home : "Vamosoroszi",
		away : "Milota",
		place : "Vamosoroszi sportpalya",
		date : "3 Nap 6 Ora 22 perc",
		time : "18:00",
		refree : "Vak Varju"
	}

});

controllers.controller('NavbarCtrl', function($scope, $location) {
	$scope.getClass = function(path) {
		if ($location.path().substr(0, path.length) === path) {
			return 'active';
		}
		return '';
	}
});


//controllers.controller('BlogPostCtrl', function($scope, $routeParams, $http) {
//	$http.get('blog/get/' + $routeParams.blogPostId).success(function(data) {
//		$scope.blogPost = data;
//	});
//});