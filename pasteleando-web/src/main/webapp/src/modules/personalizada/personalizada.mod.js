(function (ng) {

    var mod = ng.module("personalizadaModule", ['ui.router']);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/personalizada/';

            $urlRouterProvider.otherwise("/home");

            $stateProvider.state('personalizada', {
                url: '/personalizada',
                abstract: true,
                data: {
                    requireLogin: true,
                    roles: ["cliente"]
                }
                ,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'personalizada.html',
                        controller: 'personalizadaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('personalizadaList', {
                url: '/list',
                parent: 'personalizada',
                data: {
                    requireLogin: true,
                    roles: ["cliente"]
                }
                ,
                views: {
                    'listView': {
                        templateUrl: basePath + 'personalizada.list.html'
                    }
                }
            }).state('personalizadaCreate', {
                url: '/create',
                data: {
                    requireLogin: true,
                    roles: ["cliente"]
                }
                ,
                views: {
                    'createView': {
                        controller: 'personalizadaCreateCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'create/personalizada.create.html'
                    }
                }

            }) .state('personalizadaDetail', {
                url: '/{personalizadaId:int}/detail',
                parent: 'personalizada',
                param: {personalizadaId: null},
                data: {
                    requireLogin: true,
                    roles: ["cliente"]
                }
                ,
                views: {
                    'listView': {
                        templateUrl: basePath + 'personalizada.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'personalizada.detail.html',
                        controller: 'personalizadaDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);


