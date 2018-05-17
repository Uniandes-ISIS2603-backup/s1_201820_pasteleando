(function(ng)
{
    var mod = ng.module('carritoModule', ['ui.router']);
     mod.constant("carritoContext", "api/carritos");
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider)
        {
            var basePath = 'src/modules/Carrito/';
            
             $urlRouterProvider.otherwise("/home");

            $stateProvider.state('carrito', {
                url: '/carrito',
               data: {
                    requireLogin: true,
                    roles: ["admin", "cliente"]
                }
                ,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'carrito.html',
                        controller: 'carritoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('carritoList',
            {
                url:'/list',
                parent:'',
                data: {
                    requireLogin: true,
                    roles: ["admin"]
                }
                ,
                  views:
                          {
                              listView:
                            {
                                templateUrl:basePath+'carrito.list.html'
                                
                            }
                          }
            }).state('carritoDetail', {
                url: '/{carritoId: int}/detail',
                parent:'',
                param:{carritoId : null},
                data: {
                    requireLogin: true,
                    roles: ["admin", "cliente"]
                }
                ,
                views: {
                   'listView': {
                        templateUrl: basePath + 'carrito.list.html',
                        controller: 'carritoDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'carrito.detail.html',
                        controller: 'carritoDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
                 });
        }]);
            
    
})(window.angular);
