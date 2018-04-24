(function(ng)
{
    var mod = ng.module('carritoModule', ['ui.router']);
     mod.constant("carritoContext", "api/carritos");
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider)
        {
            var basePath = '/carritoDetail';
            
            $stateProvider.state('carritos',
            {
                url:'/carritos',
                  views:
                          {
                              mainView:
                            {
                                templateUrl:basePath+'carrito.html',
                                controller:'carritoCtrl',
                                controllerAs:'ctrl'
                            }
                          }
            }).state('carritoDetail', {
                url: '{carritoId: int}/detail',
                parent:'',
                param:{carritoId : null},
                views: {
                   'listView': {
                        templateUrl: basePath + 'carrito.list.html'
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
