(function(ng)
{
    var mod = ng.module('facturaModule');
   mod.controller("facturaCtrl", ['$scope', '$http','facturaContext', '$state',
        function ($scope, $http, context,$state) {
            var vm = this;
            var idFactura = $state.params.id;
            $http.get(context).then(function(response)
            {
                 $scope.facturasRecords = response.data;
                 vm.facturas = $scope.facturasRecords;
                 for(var i = 0; i< vm.facturas.length;i++){
                     vm.facturas[i].fecha = vm.facturas[i].fecha.split("T")[0];
                 }
            })
            $scope.eliminarFactura = function(facturaId)
            {
                     $http.delete(context + '/' + facturaId, {}).then(function (response)
                     {
                     $state.reload();
                });
            };
        }
    ]);
})(window.angular); 