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
                 $rootScope.precio = 0;
                 
                $http.get('data/catalogo.json').then(function (response2) {
                    for(pastel in response2.data)
                    {
                        if(response2.data[pastel].id == catalogoId.catalogoId)
                        {
                            $rootScope.precio = response2.data[pastel].precio;
                        }
                    }
                console.log($rootScope.precio);
                $rootScope.misPedidos.push(
                        {
                            "idCliente": sessionStorage.id,
                            "idCatalogo": catalogoId,
                            "seRecogeEnPasteleria":"si",
                            "precio":$rootScope.precio
                        });
                $rootScope.todosLosPedidos.push(
                        {
                            "idCliente": sessionStorage.id,
                            "idCatalogo": catalogoId,
                            "seRecogeEnPasteleria":"si",
                            "precio":$rootScope.precio
                        });
                console.log($rootScope.misPedidos);

                $state.go('pedidoList', {}, {reload: true});
            })
            
            };
        }
    ]);
}
        
        
)(window.angular);

