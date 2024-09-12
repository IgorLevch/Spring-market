angular.module('market').controller('registrationController', function($scope, $http, $location, $localStorage){
        const contextPath = 'http://localhost:5555/auth/';



    $scope.functionRegistration =  function ()  {
    $http.post(contextPath + 'registration', $scope.reguser).then(function(response){
         if(response.data.token){ // ожидаем, что после регистрации сразу придет токен
                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token; // подшиваем токен к стандартным хедерам
                        $localStorage.winterMarketUser = {username: $scope.reguser.username, token: response.data.token};  // юзера и токен кладем в хранилище localStorage.winterMarketUser
                        $localStorage.reguser = null; // мы затерли польз-ля и (ниже) перешли на главн. стр-цу

        $location.path("/"); // мы перешли в корень нашего приложения (на базовую страницу)

            }

    });
    }

});