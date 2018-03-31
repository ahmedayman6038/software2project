var myApp = angular.module("myApp",[]);
myApp.controller("myCtr",function ($scope,$http){
	 $scope.init = function(id)
	  {
		 $scope.storeId = id;
		 $http.get("/getProductsNumber/"+$scope.storeId)
		  .then(function(response) {
		      $scope.number = response.data;
		  });
		 $http.get("/getViewed/"+$scope.storeId)
		  .then(function(response) {
		      $scope.viewed = response.data;
		  });
		 $http.get("/getBuyed/"+$scope.storeId)
		  .then(function(response) {
		      $scope.buyed = response.data;
		  });
		 $http.get("/getSoldOut/"+$scope.storeId)
		  .then(function(response) {
		      $scope.products = response.data;
		  });
		 setInterval(function(){
			 $http.get("/getProductsNumber/"+$scope.storeId)
			  .then(function(response) {
			      $scope.number = response.data;
			  });
			 $http.get("/getViewed/"+$scope.storeId)
			  .then(function(response) {
			      $scope.viewed = response.data;
			  });
			 $http.get("/getBuyed/"+$scope.storeId)
			  .then(function(response) {
			      $scope.buyed = response.data;
			  });
			 $http.get("/getSoldOut/"+$scope.storeId)
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