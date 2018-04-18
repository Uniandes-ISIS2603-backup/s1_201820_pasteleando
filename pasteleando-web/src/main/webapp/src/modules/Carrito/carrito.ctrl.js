(function (ng) {
    var mod = ng.module("carritoModule");
    mod.constant("carritoContext", "api/carritos");
    mod.controller('carritoCtrl', ['$scope', '$http', 'carritoContext', '$state',

        function ($scope, $http, carritoContext, $state) {

            $http.get(carritoContext).then(function (response) {
                $scope.carritoRecords = response.data;
            });
        }
    ]);
}
)(window.angular);

