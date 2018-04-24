/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {
    var mod = ng.module("promocionModule");
    mod.constant("promocionContext", "api/promocion");
    mod.controller('promocionCreateCtrl', ['$scope', '$http', 'promocionContext', '$state', '$rootScope',
        
        function ($scope, $http, promocionContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};
           
            $scope.createpromocion = function () {
                $scope.data.fecha = $scope.data.fecha + "T00:00:00-05:00";
                $http.post(promocionContext, $scope.data).then(function (response) {
                $state.go('promocion', {promocionId: response.data.id}, {reload: true});
                });
            };
           
        }
    ]);
}
)(window.angular);