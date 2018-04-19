(function (ng) {
    var mod = ng.module("personalizadaModule");
    mod.constant("personalizadaContext", "api/personalizada");
    mod.controller('personalizadaDetailCtrl', ['$scope', '$http', 'personalizadaContext', '$state', '$filter',
        function ($scope, $http, personalizadaContext, $state, $filter) {

            if (($state.params.personalizadaId !== undefined) && ($state.params.personalizadaId !== null)) {
                $http.get('data/personalizada.json').then(function (response) {
                    $scope.personalizadaRecords = response.data;
                    $scope.currentPersonalizada = $filter('filter')($scope.personalizadaRecords, {id: $state.params.personalizadaId}, true)[0];
                });
            }
        }
    ]);
}
)(window.angular);