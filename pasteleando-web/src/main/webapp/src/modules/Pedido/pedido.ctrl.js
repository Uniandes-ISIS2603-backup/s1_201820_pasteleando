/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function(ng)
 {
     var mod = ng.module('pedidoModule');
     
     mod.constant("pedidoContext", "api/pedido");
     mod.controller("pedidoCtrl", ['$scope', '$http','pedidoContext', '$rootScope',
         
         function ($scope, $http, context, $rootScope) 
         {
             
                  $scope.pedidoRecords = $rootScope.misPedidos;
                  
             console.log($rootScope.misPedidos);
             console.log($rootScope.todosLosPedidos);
         }
     ]);
 })
 (window.angular);

