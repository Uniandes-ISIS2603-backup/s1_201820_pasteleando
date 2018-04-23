(function (ng) {
    var mod = ng.module("personalizadaModule");
    mod.constant("personalizadaContext", "api/personalizada");
    mod.controller('personalizadaCtrl', ['$scope', '$http', 'personalizadaContext',
        function ($scope, $http, personalizadaContext) {
            $http.get('data/personalizada.json').then(function (response) {
                $scope.personalizadaRecords = response.data;
            });
        }
    ]);
}
)(window.angular);

