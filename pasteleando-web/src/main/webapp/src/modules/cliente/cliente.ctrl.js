(function(ng)
{
    var mod = ng.module('clienteModule');
   mod.controller("clienteCtrl", ['$scope', '$http','clienteContext',
        function ($scope, $http, context) {
            $http.get('data/clientes.json').then(function(response)
            {
                 $scope.clientesRecords = response.data;
                 console.log($scope.clientesRecords);
            })
           
        }
    ]);
})(window.angular);