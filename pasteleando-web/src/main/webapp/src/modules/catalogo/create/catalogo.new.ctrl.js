/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.controller('catalogoCreateCtrl', ['$scope', '$http', 'catalogoContext', '$state', '$rootScope',
        
        function ($scope, $http, catalogoContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};
           
            $scope.createcatalogo = function () {
                $http.post(catalogoContext, $scope.data).then(function (response) {
                $state.go('catalogo', {catalogoId: response.data.id}, {reload: true});
                });
            };
           
        }
    ]);
}
)(window.angular);