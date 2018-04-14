var myApp = angular.module("myApp",[]);
var myApp2 = angular.module("myApp2",[]);
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
myApp2.controller("myCtr2",function ($scope,$http){
	$scope.totalprice = 0;
	  $scope.check = function() {
		  var quan = $scope.quantity;
		  if(quan == null || quan === ""){
			  quan = 0;
			}
		  $http.get("/calculate/"+$scope.price+"/"+quan)
		  .then(function(response) {
		      $scope.totalprice = response.data;
		  });
	    }
});