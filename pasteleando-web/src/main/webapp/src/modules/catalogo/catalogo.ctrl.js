(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.controller('catalogoCtrl', ['$scope', '$http', 'catalogoContext', '$rootScope', '$state',
        function ($scope, $http, catalogoContext, $rootScope,$state) {
            $http.get('data/catalogo.json').then(function (response) {
                $scope.catalogoRecords = response.data;
            })
            
            
           
           $scope.agregarAPedido = function(catalogoId)
            {   
                
                $rootScope.misPedidos.push(
                        {
                            "idCliente": sessionStorage.id,
                            "idCatalogo": catalogoId,
                            "seRecogeEnPasteleria":"si"
                        });
                $rootScope.todosLosPedidos.push(
                        {
                            "idCliente": sessionStorage.id,
                            "idCatalogo": catalogoId,
                            "seRecogeEnPasteleria":"si"
                        });
                $state.go('pedidoList', {}, {reload: true});

            };
        }
    ]);
}
        
        
)(window.angular);

