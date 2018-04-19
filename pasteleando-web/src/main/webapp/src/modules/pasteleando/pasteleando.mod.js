(function (ng) {
var mod = ng.module("pasteleandoModule", []);
    mod.constant("pasteleandoContext", "api/pasteleando");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/pasteleando/';
            $urlRouterProvider.otherwise("/pasteleandoList");

            $stateProvider.state('pasteleandoList', {
                url: '/pasteleando',
                views: {
                    'mainView': {
                        controller: 'pasteleandoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pasteleando.list.html'
                    }
                }
            }).state('pasteleandoCreate', {
                url: '/pasteleando/create',
                views: {
                    'mainView': {
                        controller: 'pasteleandoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pasteleando.create.html'
                    }
                }

            }).state('pasteleandoEdit', {
                url: '/pasteleando/:pasteleandoId',
                param: {
                    pasteleandoId: null
                },
                views: {
                    'mainView': {
                        controller: 'pasteleandoCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pasteleando.create.html'
                    }
                }
            });
        }]);

})(window.angular);

