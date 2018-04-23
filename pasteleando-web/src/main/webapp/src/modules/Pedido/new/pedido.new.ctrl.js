/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) 
{
    var mod = ng.module("pedidoModule");
    mod.constant("pedidoContext", "api/pedido");
    mod.controller('pedidoNewCtrl', ['$scope', '$http', 'pedidoContext', '$state', '$rootScope',
 
        function ($scope, $http, pedidoContext, $state, $rootScope) 
        {
            $rootScope.edit = false;

            $scope.data = {};

            $scope.createPedido = function () 
            {
                $http.post(pedidoContext, $scope.data).then(function (response) 
                {
                    $state.go('pedidoList', {pedidoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)
(window.angular);
