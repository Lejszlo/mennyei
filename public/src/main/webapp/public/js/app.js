var phonecatApp = angular.module('phonecatApp', [ "chart.js", 'ngRoute', 'controllers', 'filters' ]);

phonecatApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/members', {
		templateUrl : 'public/partials/members.html',
		controller : 'MembersListCtrl'
	}).when('/members/:memberId', {
		templateUrl : 'public/partials/memberProfile.html',
		controller : 'MemberProfileCtrl'
	}).when('/blog', {
		templateUrl : 'public/partials/blog/blog.html',
		controller : 'BlogCtrl'
	}).when('/blog/:blogPostId', {
		templateUrl : 'public/partials/blog/blogPost.html',
		controller : 'BlogPostCtrl'
	}).otherwise({
		redirectTo : '/members'
	});
} ]);