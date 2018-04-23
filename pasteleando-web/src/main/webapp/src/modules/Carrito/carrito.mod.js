(function(ng)
{
    var mod = ng.module('carritoModule', ['ui.router']);
     mod.constant("carritoContext", "api/carritos");
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider)
        {
            var basePath = 'src/modules/carrito/';
            
            $stateProvider.state('carritoList',
            {
                url:'/carritos/list',
                  views:
                          {
                              mainView:
                            {
                                templateUrl:basePath+'carrito.list.html',
                                controller:'carritoCtrl',
                                controllerAs:'ctrl'
                            }
                          }
            }).state('carritoDetail', {
                url: '{carritoId: int}/detail',
                parent:'carritos',
                param:{carritoId = null},
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
