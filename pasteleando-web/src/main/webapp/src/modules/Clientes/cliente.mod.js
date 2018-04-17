(function (ng) {
var mod = ng.module("clienteModule", []);
    mod.constant("clienteContext", "api/clientes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Clientes/';
            $urlRouterProvider.otherwise("/clienteList");

            $stateProvider.state('clienteList', {
                url: '/Clientes',
                views: {
                    'mainView': {
                        controller: 'clienteCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'cliente.list.html'
                    }
                }
            }).state('clienteCreate', {
                url: '/cliente/create',
                views: {
                    'mainView': {
                        controller: 'clienteCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'cliente.create.html'
                    }
                }

            }).state('clienteEdit', {
                url: '/cliente/:clienteId',
                param: {
                    clienteId: null
                },
                views: {
                    'mainView': {
                        controller: 'clienteCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'cliente.create.html'
                    }
                }
            });
        }]);

})(window.angular);

