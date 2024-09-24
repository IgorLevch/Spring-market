//Если прописывается список модулей, которые подключаются:  ['ngStorage']
//то angular.js считает, что это создание нового приложения.
//Если же мы в слове module прописали только имя приложения:
//angular.module('market').controller('cartController', ... )  .....--- см. файл cart.js например, то это мы подключаемся к
//основному js-файлу.и говорим, что хотим в нем создать контроллер , который называется cartController.



//  функция по запуску приложения:
(function()  {  // это функция ,которая создает приложение под названием market
    angular
       .module('market', ['ngRoute', 'ngStorage'])  // подключаем 2 модуля: ngRoute  --позволяет прыгать постранично и  ngStorage  - локальное хранилище
       .config(config)   // конфигурируем с пом-ю ф-ции config
       .run(run);    //и запустить с пом-ю функции run


//   Конфиг на вход получает routeProvider
       function config($routeProvider){
            $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller:  'welcomeController'

            })
            .when('/store', {
                            templateUrl: 'store/store.html',
                            controller:  'storeController'

                        })
            .when('/cart', {
                            templateUrl: 'cart/cart.html',
                            controller:  'cartController'

                        })
            .when('/orders', {
                            templateUrl: 'orders/orders.html',
                            controller:  'ordersController'

                        })
            .when('/order_pay/:orderId', {
                            templateUrl: 'order_pay/order_pay.html',
                            controller:  'orderPayController'

                        })
                        .otherwise({
                        redirectTo:'/'
                        });

       }

        function run($rootScope, $http, $localStorage){
       //   проверка того, что тут нужно токен провалидировать, запуск:
           if($localStorage.winterMarketUser) {  // проверка стандартного токена
               try{
                   let jwt = $localStorage.winterMarketUser.token;
                   let payload = JSON.parse(atob(jwt.split('.')[1]));
                   let currentTime = parseInt(new Date().getTime()/1000);
                   if(currentTime > payload.exp){
                       console.log("Token is expired!!!");
                       delete $localStorage.winterMarketUser;
                       $http.defaults.headers.common.Authorization = '';

                   }
               } catch(e) {}

               $http.defaults.headers.common.Authorization = 'Bearer '  + $localStorage.winterMarketUser.token;
           }  // присвоение токена
}

})();


// индекс контролллер (управлялка навигационной панельки):
angular.module('market').controller('indexController', function($scope, $http, $localStorage){
    console.log(123);
        // авторизация:
    $scope.tryToAuth = function(){
        $http.post('http://localhost:5555/auth/auth', $scope.user)
        .then(function successCallback(response){
            if(response.data.token){
                $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                $localStorage.winterMarketUser = {username: $scope.user.username, token: response.data.token};

                $scope.user.username = null;
                $scope.user.password = null;

                $location.path('/');

            }

        }, function errorCallback(response){}
    );
    };

// логаут юзера:
    $rootscope.tryToLogOut = function (){
        $scope.clearUser();
        $scope.user = null;
        $location.path('/');
    };

// выход юзера:
    $scope.clearUser = function (){
        delete $localStorage.winterMarketUser;
        $http.defaults.headers.common.Authorization = '';
    };

    // залогинен ли юзер или нет:

    $rootscope.isUserLoggedIn = function(){
        if($localStorage.winterMarketUser){
            return true;
        }  else{
            return false;
        }
    };

    // $scope.authCheck = function(){
    //     $http.get('http://localhost:5555/core/auth_check').then(function (response){
    //         alert(response.data.value);
    //     }
    // );

    // };













    // const contextPath = 'http://localhost:8080/api/v1/products';

    // console.log(123);

    // $scope.loadProducts = function(pageIndex=1){

    // $http ({
    //     method:'GET',
    //     url: contextPath,
    //     params: {
    //         title_part: $scope.filter ? $scope.filter.title_part: null,
    //         min_mark: $scope.filter ? $scope.filter.min_mark: null,
    //         max_mark: $scope.filter ? $scope.filter.max_mark: null,
    //         p : page
    //     }
    // })  .then(function (response) {
    //         $scope.ProductsList = response.data.content;
    // });

    // };


    // $scope.deleteProduct = function(productId){
    //     $http.delete(contextPath + productId)
    //     .then(function (response){
    //         $scope.loadProducts();
    //         console.log(response.data)

    //     });

    // }


    //  $scope.findProduct = function(productId){
    //     $http.get(contextPath+productId)
    //         .then(function (response){
    //         $scope.ProductsList = response.data
    //         });

    //  }

    //  $scope.findProductByTitle = function(productTitle){
    //     $http.get(contextPath+productTitle)
    //         .then(function(response){
    //         $scope.productId = response.data

    //         });
    //  }

    // $scope.createProductJson = function(){
    //     console.log($scope.newProductJson);
    //     $http.post(contextPath, $scope.newProductJson)
    //     .then(function(response){
    //     $scope.loadProducts();
    //     });
    // }


    // $scope.loadProducts();


  });