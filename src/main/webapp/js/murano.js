'use strict';

var sculptureApp = angular.module('sculptureApp',[]);
        
sculptureApp.config(function($routeProvider) {
    $routeProvider
        .when('/', {templateUrl: 'partials/partial1.html', controller: 'GiftPartsController'})
        .when('/view2', {templateUrl: 'partials/partial2.html', controller: 'GiftPartsController'})
        .otherwise({redirectTo: '/'});
  });
  
  
sculptureApp.factory('partsService', function($http, $q) {
	//var parts = [
    //    { name: 'Left Eye', price: 10 }, 
    //    { name: 'Pelvis', price: 20 }, 
    //    { name: 'Right Eye', price: 10 }
    //];
    
    var factory = {};
    
    factory.getAllParts = function() {
    	
    	var deferred = $q.defer();
    	
    	$http.get('http://localhost:8080/murano/service/gifts/').
    		success(function(data, status, headers, config) {
    			deferred.resolve(data);
    		}).
    		error(function(data, status, headers, config) {
    			deferred.reject("An error occured while fetching items");
    		});
    	
    	return deferred.promise;
    };
    
    factory.markAsSold = function(item) {
    	
    	var deferred = $q.defer();
    	
    	item.sold = true;
    	
    	$http.put('http://localhost:8080/murano/service/gift', item).
			success(function(data, status, headers, config) {
				console.log('http://localhost:8080/murano/service/gift');
				deferred.resolve(data);
			}).
			error(function(data, status, headers, config) {
				console.log('ERROR: http://localhost:8080/murano/service/gift');
				deferred.reject("An error occured while marking item " + item.name + " as sold");
			});
	    	
    	return deferred.promise;
    };
    
    return factory;
});
  
sculptureApp.controller('GiftPartsController',function($scope, partsService) {
	
    $scope.parts = [];
    $scope.basket = {
    	totalPrice: 0, 
    	contents: [],
    	
    	addItem : function(item) {
    		if (this.contents.indexOf(item) == -1) {
    			this.contents.push(item);
    			this.totalPrice = this.totalPrice + item.price;
    		}
    	},
    	removeItem : function(item) {
    		var index = this.contents.indexOf(item);
    		if (index > -1) {
    			this.contents.splice(index, 1);
    			this.totalPrice = this.totalPrice - item.price;
    		}
    	},
    	clearBasket : function() {
    		this.totalPrice = 0;
    		this.contents = [];
    	}
    };
    
    $scope.refreshParts = function(){
    	partsService.getAllParts().then(function(data){
          $scope.parts = data;
        },
        function(errorMessage){
          $scope.error=errorMessage;
        });
      };
    
    $scope.buyParts = function() {
    	$scope.parts.push({name: $scope.newPart.name, price: $scope.newPart.price});
    };
    
    $scope.addToBasket = function(partToAdd) {
    	//console.log('id: ' + partToAdd.name);
    	$scope.basket.addItem(partToAdd);
    };
    
    $scope.removeFromBasket = function(partToAdd) {
    	//console.log('id: ' + partToAdd.name);
    	$scope.basket.removeItem(partToAdd);
    };
    
    $scope.checkout = function() {
    	partsService.markAsSold($scope.basket.contents[0]).then(function(data){
    		$scope.basket.clearBasket();
    	    $scope.refreshParts();
    	},
    	function(errorMessage){
    		$scope.error=errorMessage;
        });
    };
    
    
    $scope.refreshParts();
});



