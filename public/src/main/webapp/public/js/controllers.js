var controllers = angular.module('controllers', []);

controllers
		.controller(
				'MemberProfileCtrl',
				function($scope, $routeParams) {
					$scope.projects = [
							{
								name : "Projekt1",
								description : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla imperdiet in turpis in semper. Duis vel nulla efficitur, malesuada sapien et, volutpat eros. Vivamus bibendum dignissim porttitor. Mauris scelerisque mattis viverra."
							},
							{
								name : "Projekt2",
								description : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla imperdiet in turpis in semper. Duis vel nulla efficitur, malesuada sapien et, volutpat eros. Vivamus bibendum dignissim porttitor. Mauris scelerisque mattis viverra."
							},
							{
								name : "Projekt3",
								description : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla imperdiet in turpis in semper. Duis vel nulla efficitur, malesuada sapien et, volutpat eros. Vivamus bibendum dignissim porttitor. Mauris scelerisque mattis viverra."
							},
							{
								name : "Projekt4",
								description : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla imperdiet in turpis in semper. Duis vel nulla efficitur, malesuada sapien et, volutpat eros. Vivamus bibendum dignissim porttitor. Mauris scelerisque mattis viverra."
							},
							{
								name : "Projekt5",
								description : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla imperdiet in turpis in semper. Duis vel nulla efficitur, malesuada sapien et, volutpat eros. Vivamus bibendum dignissim porttitor. Mauris scelerisque mattis viverra."
							}, ]
					
					$scope.categories = [ {
						name : "Programming",
					}, {
						name : "Documentation",
					}, {
						name : "Management",
					}, {
						name : "Language",
					} ]

					$scope.labels = [ "Java", "Java Script", "C", "C++", "C#",
							"PHP", "Scala", "Perl" ];
					$scope.data = [ 300, 500, 100, 200, 150, 350, 20, 50 ];

					$scope.properties = [ {
						name : "Name",
						value : "Kiss Pista"
					}, {
						name : "Age",
						value : "29 years old"
					}, {
						name : "Title",
						value : "Lead Developer"
					}, {
						name : "Start date",
						value : "2010.02.01"
					}, {
						name : "Actual projects",
						value : "Project1, Project3"
					} ]

				});

controllers.controller('MembersListCtrl', function($scope) {
	$scope.members = [ {
		name : "Kis Pista",
		value : 1
	}, {
		name : "Kleinheisler Laszlo",
		value : 2
	}, {
		name : "Gera Zoltan",
		value : 3
	}, {
		name : "Nagy Adam",
		value : 4
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