'use strict';

/* Controllers */

angular.module('myApp.controllers', []).
  controller('MyCtrl1', [function($scope) {
    $scope.sculptureParts = [
      {name: 'Left Eye', price: 10.00},
      {name: 'Right Eye', price: 11.00}
    ];
  }])
  .controller('MyCtrl2', [function() {

  }]);