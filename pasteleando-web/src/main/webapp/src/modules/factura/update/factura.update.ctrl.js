/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {
    var mod = ng.module("facturaModule");
    mod.constant("facturaContext", "api/facturas");
    mod.controller("facturaUpdateCtrl", ['$scope', '$http', 'facturaContext', '$state',
         function ($scope, $http, context,$state) {
            var vm = this;
            $http.get(context + '/' + $state.params.facturaId).then(function(response)
            {
                 $scope.facturasRecords1 = response.data;
                 console.log($scope.facturasRecords1);
                 vm.facturas = $scope.facturasRecords1;
                 $scope.facturasRecords1.fecha = $scope.facturasRecords1.fecha.split("T")[0];
                 
            })
            $scope.updateFactura = function (idFactura)
            {
                $http.put(context + "/" + idFactura, $scope.data).then(function (response) {
                $state.go('facturas',{facturaId: response.data.id},{reload:true});
                });
            }
        }
    ]);
}
)(window.angular);