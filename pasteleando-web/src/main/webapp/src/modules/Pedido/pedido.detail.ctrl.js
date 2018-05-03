/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) 
{
    var mod = ng.module("pedidoModule");
    mod.constant("pedidoContext", "api/pedido");
    mod.controller('pedidoDetailCtrl', ['$scope', '$http', 'pedidoContext', '$state', '$filter',
        
        function ($scope, $http, pedidoContext, $state, $filter) 
        {
            if (($state.params.pedidoId !== undefined) && ($state.params.pedidoId !== null)) 
            {
               $http.get(pedidoContext + '/' + $state.params.pedidoId).then(function (response) 
                {
                    $scope.currentPedido = response.data; 
                });
            }
        }
    ]);
}
)
(window.angular);