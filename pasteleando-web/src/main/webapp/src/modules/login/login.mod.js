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
                    requireLogin: true,
                    roles: ["admin","cliente"]
                }
                ,
                views: {
                    'logout': {
                      controller: 'logoutCtrl',
                    templateUrl: basePath + 'logout.html'
                       
                    }
                }
            });
        }
    ]);
})(window.angular);

