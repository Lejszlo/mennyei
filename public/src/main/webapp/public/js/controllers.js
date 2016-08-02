var controllers = angular.module('controllers', []);

controllers
		.controller(
				'ClubCtrl',
				function($scope, $routeParams) {
					$scope.categories = [ {
						name : "Hazai",
					}, {
						name : "Idegenbeli",
					}]

					$scope.labels = [ "Gyozelem", "Dontetlen", "Vereseg" ];
					$scope.data = [ 10, 3, 1 ];

					$scope.properties = [ {
						name : "Teljes nev",
						value : "Vamosoroszi Kozsegi Sport Egyesulet"
					}, {
						name : "Alapitas",
						value : "1965"
					},  {
						name : "Telepules",
						value : "Vamosoroszi"
					}]
					
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
					
					$scope.clubs = [ {
						name : "Vamosoroszi KSE",
						point : 30,
					}, {
						name : "Tarpa SE",
						point : 21,
					}, {
						name : "Kolcse",
						point : 19,
					}, {
						name : "Milota",
						point : 23,
					} ]

				});

controllers.controller('NavbarCtrl', function($scope, $location) {
	$scope.getClass = function(path) {
		if ($location.path().substr(0, path.length) === path) {
		    return 'active';
		}
		return '';
	}
});

controllers.controller('BlogCtrl', function($scope, $http) {
	 $http.get('blog/get').success(function(data) {
	      $scope.blogPosts = data;
	 });	
});

controllers.controller('BlogPostCtrl', function($scope, $routeParams, $http) {
	$http.get('blog/get/' + $routeParams.blogPostId).success(function(data) {
	      $scope.blogPost = data;
	});
});