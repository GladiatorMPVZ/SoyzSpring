(function () {
    angular
        .module('searcher', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/search', {
                templateUrl: 'search/search.html',
                controller: 'searchController'
            })
            // .when('/store', {
            //     templateUrl: 'store/store.html',
            //     controller: 'storeController'
            // })
            // .when('/cart', {
            //     templateUrl: 'cart/cart.html',
            //     controller: 'cartController'
            // })
            // .when('/orders', {
            //     templateUrl: 'orders/orders.html',
            //     controller: 'ordersController'
            // })
            // .otherwise({
            //     redirectTo: '/'
            // });
            .otherwise({
                    redirectTo: '/'
                });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.soyzUser) {
            try {
                let jwt = $localStorage.soyzUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.soyzUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }

            if ($localStorage.soyzUser) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.soyzUser.token;
            }
        }
    }
})();


angular.module('searcher').controller('indexController', function ($scope, $http) {
    $scope.tryToAuth = function () {
        $http.post('http://localhost:8100/api/v1/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.marchMarketUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    $location.path('/');
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $location.path('/');
    };

    $scope.clearUser = function () {
        delete $localStorage.soyzUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.soyzUser) {
            return true;
        } else {
            return false;
        }
    };


})