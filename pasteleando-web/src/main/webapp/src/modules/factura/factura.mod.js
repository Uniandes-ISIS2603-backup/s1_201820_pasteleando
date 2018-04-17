(function(ng)
{
    var mod = ng.module('facturaModule', ['ui.router']);
     mod.constant("facturaContext", "api/facturas");
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider)
        {
            var basePath = 'src/modules/factura/';
            
            $stateProvider.state('facturasList',
            {
                url:'/facturas/list',
                  views:
                          {
                              mainView:
                            {
                                templateUrl:basePath+'factura.list.html',
                                controller:'facturaCtrl',
                                controllerAs:'ctrl'
                            }
                          }
            });
        }]);
    
})(window.angular);

