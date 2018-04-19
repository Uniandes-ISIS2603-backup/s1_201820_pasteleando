(function(ng)
{
    var mod = ng.module('clienteModule', ['ui.router']);
     mod.constant("clienteContext", "api/clientes");
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider)
        {
            var basePath = 'src/modules/cliente/';
            
            $stateProvider.state('clientesList',
            {
                url:'/clientes/list',
                  views:
                          {
                              mainView:
                            {
                                templateUrl:basePath+'cliente.list.html',
                                controller:'clienteCtrl',
                                controllerAs:'ctrl'
                            }
                          }
            });
        }]);
    
})(window.angular);

