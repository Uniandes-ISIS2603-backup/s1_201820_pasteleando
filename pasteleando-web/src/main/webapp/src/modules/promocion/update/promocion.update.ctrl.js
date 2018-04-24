/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {
    var mod = ng.module("promocionModule");
    mod.constant("promocionContext", "api/promocion");
    mod.controller("promocionUpdateCtrl", ['$scope', '$http', 'promocionContext', '$state',
         function ($scope, $http, context,$state) {
            var vm = this;
            $http.get(context + '/' + $state.params.promocionId).then(function(response)
            {
                 $scope.promocionRecords1 = response.data;
                 console.log($scope.promocionRecords1);
                 vm.promocion = $scope.promocionRecords1;
                 $scope.promocionRecords1.fecha = $scope.promocionRecords1.fecha.split("T")[0];
                 
            })
            $scope.updatepromocion = function (idpromocion)
            {
                $http.put(context + "/" + idpromocion, $scope.data).then(function (response) {
                $state.go('promocion',{promocionId: response.data.id},{reload:true});
                });
            }
        }
    ]);
}
)(window.angular);