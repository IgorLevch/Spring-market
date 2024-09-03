angular.module('market').controller('cartController', function($scope, $http, $location, $localStorage){
        const contextPath = 'http://localhost:5555/cart/';
        const coreContextPath = 'http://localhost:5555/core/';


        $scope.loadCart = function(){

            $http.get(contextPath + 'api/v1/cart').then(function(response){

            $scope.cart = response.data;

            });
            }


        $scope.deleteFromCart = function(){

        $http.get(contextPath +'api/v1/cart/delete').then(function(response){

             $scope.loadCart();
             });
             }


        $scope.deleteItem = function(productId){

              $http.get(contextPath +'api/v1/cart/delete/'+productId).then(function(response){

              $scope.loadCart();
              });
              }


         $scope.createOrder =  function ()  {
              $http.post(coreContextPath + 'api/v1/orders').then(function(response){
              alert('Order checkout');
              $scope.loadCart();
              });
              }



         $scope.loadCart();

});