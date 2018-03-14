var myApp = angular.module("myApp",[]);
myApp.controller("myCtr",function ($scope,$http){
	 $scope.init = function(id)
	  {
		 $scope.userId = id;
		 $http.get("/getViewed/"+$scope.userId)
		  .then(function(response) {
		      $scope.viewed = response.data;
		  });
		 $http.get("/getBuyed/"+$scope.userId)
		  .then(function(response) {
		      $scope.buyed = response.data;
		  });
		 $http.get("/getSoldOut/"+$scope.userId)
		  .then(function(response) {
		      $scope.products = response.data;
		  });
		 setInterval(function(){
			 $http.get("/getViewed/"+$scope.userId)
			  .then(function(response) {
			      $scope.viewed = response.data;
			  });
			 $http.get("/getBuyed/"+$scope.userId)
			  .then(function(response) {
			      $scope.buyed = response.data;
			  });
			 $http.get("/getSoldOut/"+$scope.userId)
			  .then(function(response) {
			      $scope.products = response.data;
			  });
			}, 60000);
	  }
	  $scope.accept = function(id){
		  $http({
		        url: '/acceptStore/'+id,
		        method: "POST"
		    })
		    .then(function(response) {
		    	console.log(response);
		    }, 
		    function(response) {
		    	console.log("Faild");
		    });
	  }
});