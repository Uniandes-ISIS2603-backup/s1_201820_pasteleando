(function(ng)
{
    var mod = ng.module('facturaModule');
    mod.constant("facturaContext", "api/pasteleando");
   mod.controller("facturaListCtrl", ['$scope', '$http','facturas',
        function ($scope, facturas) {
            $scope.facturasRecords = facturas.data;
        }
    ]);
})(window.angular);