(function(ng)
{
    var mod = ng.module('facturaModule', ['ui.router']);
    
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider)
        {
            var basePath = 'src/modules/factura';
            
            $stateProvider.state('facturasList',
            {
                url:'/facturas/list',
                  views:
                          {
                              mainView:
                            {
                                templateurl:'factura.list.html',
                                controller:'facturaCtrl',
                                controllerAs:'ctrl'
                            }
                          }
            });
        }]);
    
})(window.angular);

