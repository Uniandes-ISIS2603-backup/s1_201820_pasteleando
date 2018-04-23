(function (ng)
{
    var mod= ng.module("carritoModule");
    mod.constant("carritoContext", "/api/carritos");
    mod.controller('carritoDetailCtrl', ['$scope', '$http', 'carritoContext', '$state',
    function($scope, $http, carritoContext, $state)
    {
        if(($state.params.carritoId !== undefined) && ($state.params.carritoId !== null) )
        {
            $http.get(carritoCotext + '/' + $stateParams.carritoId).then(function (response) {
                $scope.currentCarrito = response.data;
            });
        }
    }
    ])
}
        )