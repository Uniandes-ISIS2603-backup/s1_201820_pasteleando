(function (ng) {
    var mod = ng.module("pastelModule");
    mod.constant("pastelContext", "api/pasteles");
    mod.controller('pastelCtrl', ['$scope', '$http', 'pastelContext', '$state',

        function ($scope, $http, pastelContext, $state) {

            $http.get(pastelContext).then(function (response) {
                $scope.pastelRecords = response.data;
            });
        }
    ]);
}
)(window.angular);