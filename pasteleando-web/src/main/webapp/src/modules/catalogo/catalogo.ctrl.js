(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.controller('catalogoCtrl', ['$scope', '$http', 'catalogoContext',
        function ($scope, $http, catalogoContext) {
            $http.get('data/catalogo.json').then(function (response) {
                $scope.catalogoRecords = response.data;
            });
        }
    ]);
}
)(window.angular);

