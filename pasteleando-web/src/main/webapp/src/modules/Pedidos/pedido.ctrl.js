/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function(ng)
{
    var mod = ng.module('pedidoModule');
    
    mod.constant("pedidoContext", "api/pedidos");
    mod.controller("pedidoCtrl", ['$scope', '$http','pedidoContext',
        
        function ($scope, $http, context) 
        {
            $http.get(context).then(function(response)
            {
                 $scope.pedidoRecords = response.data;
            })
        }
    ]);
})
(window.angular);

