(function(angular) {
  'use strict';
angular.module('ngRouteExample', ['ngRoute', 'ngResource'])

 .controller('MainController', function($scope, $route, $routeParams, $location, $http, $rootScope) {
     $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
     $scope.name = '';
     $http.get('/principal').then(function(response){
			$rootScope.principal = response.data;
			$scope.name = response.data.name;
	}, function(response){});
 })
 
 .controller('TweetController', function($scope, $routeParams, $http, $rootScope) {
     $scope.name = '';
     $scope.params = $routeParams;
     
     $scope.message = "";
     $http.get('/principal').then(function(response){
			$rootScope.principal = response.data;
			$scope.name = response.data.name;
			$http({
				  method: 'GET',
				  url: '/'+$scope.name+'/tweets'
				}).then(function successCallback(response) {
					$scope.tweets = response.data;
				  }, function errorCallback(response) {
				});
	}, function(response){});
     
     $scope.postTweet= function(){
    	 console.log($scope.message);
  		$http({
  		  method: 'POST',
  		  url: '/'+$rootScope.principal.name+'/tweets',
  		  params: {"message":$scope.message},
  		}).then(function successCallback(response) {
  			$scope.message = "";
  		  }, function errorCallback(response) {
  		});
     }

 })
 
 .controller('UsersController', function($scope, $routeParams, $http, $rootScope, UserProfile) {
     $scope.name = 'UsersController';
     $scope.msg = " - from users Controller";

	
//	$scope.userProfile = UserProfile.query();	//makes the rest call to the resource 
     if($routeParams.follow == undefined){
    	 $routeParams.follow = "all";
     }
     $scope.params = $routeParams;
//	console.log($routeParams.follow);
	$http({
		  method: 'GET',
		  url: '/user',
		  params: $scope.params,
		  data: $scope.params,
		}).then(function successCallback(response) {
			$scope.userProfile = response.data;
		  }, function errorCallback(response) {
		});
	
	$scope.toFollow = function(){
		return $routeParams.follow == "all";
	}
	
	$scope.follow = function(index){
		
		$http({
		  method: 'POST',
		  url: '/connection',
		  params: {'from':$rootScope.principal.name, 'to':$scope.userProfile[index].id},
		  data: {'from':$rootScope.principal.name, 'to':$scope.userProfile[index].id}
		}).then(function successCallback(response) {
			//alert('removing '+$scope.userProfile[index].name);
			$scope.successMessage = "You are now following "+$scope.userProfile[index].name;
			$scope.showSuccess = true;
			$scope.userProfile.splice(index, 1);
		  }, function errorCallback(response) {
		  });
	}
     
 })
 
 .controller('PostController', function($scope, $routeParams, $http, $rootScope) {
     $scope.name = 'PostController';
     $scope.params = $routeParams;
 })

.config(function($routeProvider, $locationProvider) {
	$routeProvider
	  .when('/', {
		    templateUrl: 'app/tweeter.html',
		    controller: 'TweetController'
		  })
  .when('/users/:follow', {
    templateUrl: 'app/users1.html',
    controller: 'UsersController'
  })
  .when('/tweeter/:mode', {
	    templateUrl: 'app/tweeter.html',
	    controller: 'TweetController'
  })
    .when('/post', {
	    templateUrl: 'app/post.html',
	    controller: 'PostController'
  })
.when('/logout',{
    controller : function(){
        window.location.replace('/logout');
    }, 
    template : "<div></div>"
})
	.otherwise({
	    redirectTo: '/'
	});

  // configure html5 to get links working on jsfiddle
  $locationProvider.html5Mode(true);
})

.factory('UserProfile', function($resource) {
	  return $resource('/user'); 
	});

})(window.angular);