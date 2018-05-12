(function (ng) {
var mod = ng.module("loginModule", ['ui.router']);
  
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/login/';
            $urlRouterProvider.otherwise("/home");

            $stateProvider.state('login', {
                url: '/login',
                data: {
                    requireLogin: false
                },
                views: {
                    'mainView': {
                        controller: 'loginCtrl',
                            templateUrl: basePath + 'login.html'
                    }
                }
            }).state('logout', {
                    url: '/logout',
                    data: {
                    requireLogin: false,
                    roles: []
                }
                ,
                views: {
                    'mainView': {
                    templateUrl: basePath + 'logout.html',
                        controller: 'logoutCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);

