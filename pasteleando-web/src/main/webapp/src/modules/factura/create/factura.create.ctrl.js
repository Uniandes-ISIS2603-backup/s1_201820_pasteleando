/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {
    var mod = ng.module("facturaModule");
    mod.constant("facturasContext", "api/facturas");
    mod.controller('facturaCreateCtrl', ['$scope', '$http', 'facturasContext', '$state', '$rootScope',
        
        function ($scope, $http, facturasContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};
           
            $scope.createFactura = function () {
                $scope.data.fecha = new Date();
                $scope.data.hora = $scope.data.horas + ":" + $scope.data.minutos;
                $http.post(facturasContext, $scope.data).then(function (response) {                   
                $state.go('facturas', {facturaId: response.data.id}, {reload: true});
                });
            };
            
            $scope.minutos = [];
            
            for (var i = 0; i < 60; i++) {
               if(i < 10)
               {
                    $scope.minutos.push("0"+i);
               }
               else
               {
                    $scope.minutos.push(i);
               }
            }
            
            $scope.horas = [];
            
            for (var i = 0; i < 24; i++) {
               if(i < 10)
               {
                    $scope.horas.push("0"+i);
               }
               else
               {
                    $scope.horas.push(i);
               }
            }
           
           
            
            
            
        }
    ]);
}
)(window.angular);