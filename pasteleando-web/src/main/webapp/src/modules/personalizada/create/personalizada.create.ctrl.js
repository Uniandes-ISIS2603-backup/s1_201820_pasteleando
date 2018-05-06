(function (ng) {
    var mod = ng.module("personalizadaModule");
    mod.constant("personalizadaContext", "api/personalizada");
    mod.controller('personalizadaCreateCtrl', ['$scope', '$http', 'personalizadaContext', '$state', 'personalizadaContext', '$rootScope',
        function ($scope, $http, personalizadaContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createPersonalizada = function () {
                $http.post('api/personalizada', {
                    peso: $scope.personalizadaPeso,
                    color: $scope.personalizadaColor
                }).then(function (response) {
                   
                        $state.go('personalizadaDetail', {personalizadaId: response.data.id}, {reload: true});

                    
                     
                });
            };
        }
    ]);
}
)(angular);

