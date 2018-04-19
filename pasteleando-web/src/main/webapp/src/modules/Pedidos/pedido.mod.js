/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function(ng)
{
    var mod = ng.module('pedidoModule', ['ui.router']);
    
    mod.constant("pedidoContext", "api/pedidos");
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider)
        {
            var basePath = 'src/modules/pedido/';
            
            $stateProvider.state('pedidosList',
            {
                url:'/pedidos/list',
                  views:
                          {
                              mainView:
                            {
                                templateUrl:basePath+'pedido.list.html',
                                controller:'pedidoCtrl',
                                controllerAs:'ctrl'
                            }
                          }
            });
        }]);
    
})
(window.angular);
