(function(ng)
{
    var mod = ng.module('facturaModule');
   mod.controller("facturaCtrl", ['$scope', '$http','facturaContext',
        function ($scope, $http, context) {
            $http.get(context).then(function(response)
            {
                 $scope.facturasRecords = response.data;
                 console.log($scope.facturasRecords);
            })
           
        }
    ]);
})(window.angular);