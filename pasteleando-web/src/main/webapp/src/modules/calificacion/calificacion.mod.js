(function(ng)
{
    var mod = ng.module('calificacionModule', ['ui.router']);
     mod.constant("calificacionContext", "api/clientes");
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider)
        {
            var basePath = 'src/modules/calificacion/';
            
            $stateProvider.state('calificacionList',
            {
                url:'/calificacion/list',
                  views:
                          {
                              mainView:
                            {
                                templateUrl:basePath+'calificacion.list.html',
                                controller:'calificacionCtrl',
                                controllerAs:'ctrl'
                            }
                          }
            });
        }]);
    
})(window.angular);

