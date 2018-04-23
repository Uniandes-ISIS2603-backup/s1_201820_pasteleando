/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) 
{
    var mod = ng.module("pedidoModule");
    mod.constant("pedidoContext", "api/pedido");
    mod.controller('pedidoDeleteCtrl', ['$scope', '$http', 'pedidoContext', '$state',
        
        function ($scope, $http, pedidoContext, $state) 
        {
            var idPedido = $state.params.pedidoId;
            
            $scope.deletePedido = function () 
            {
                $http.delete(pedidoContext + '/' + idPedido, {}).then(function (response) 
                {
                    $state.go('pedidoList', {pedidoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)
(window.angular);
