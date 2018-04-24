(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.controller('catalogoDetailCtrl', ['$scope', '$http', 'catalogoContext', '$state', '$filter',
        function ($scope, $http, catalogoContext, $state, $filter) {

            if (($state.params.catalogoId !== undefined) && ($state.params.catalogoId !== null)) {
                $http.get('data/catalogo.json').then(function (response) {
                    $scope.catalogoRecords = response.data;
                    $scope.currentcatalogo = $filter('filter')($scope.catalogoRecords, {id: $state.params.catalogoId}, true)[0];
                });
            }
        }
    ]);
}
)(window.angular);