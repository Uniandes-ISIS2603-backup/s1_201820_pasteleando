(function (ng)
{
    var mod= ng.module("carritoModule");
    mod.constant("carritoContext", "/api/carritos");
    mod.controller('carritoDetailCtrl', ['$scope', '$http', 'carritoContext', '$state',
    function($scope, $http, carritoContext, $state)
    {
        var carritoId = 1;
        if(($state.params.carritoId !== undefined) && ($state.params.carritoId !== null) )
        {
            $http.get(carritoContext + '/' + $stateParams.carritoId).then(function (response) {
                $scope.currentCarrito = response.data;
            });
        }
    }
    ]);
}
        );