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
                $scope.data.fecha = $scope.data.fecha + "T00:00:00-05:00";
                $http.post(facturasContext, $scope.data).then(function (response) {
                $state.go('facturas', {facturaId: response.data.id}, {reload: true});
                });
            };
           
        }
    ]);
}
)(window.angular);