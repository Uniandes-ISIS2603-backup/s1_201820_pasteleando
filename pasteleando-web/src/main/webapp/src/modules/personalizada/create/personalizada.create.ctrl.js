(function (ng) {
    var mod = ng.module("personalizadaModule");
    mod.constant("personalizadaContext", "api/personalizada");
    mod.controller('personalizadaCreateCtrl', ['$scope', '$http', 'personalizadaContext', '$state', 'personalizadaContext', '$rootScope',
        function ($scope, $http, personalizadaContext, $state, $rootScope) {
            $rootScope.edit = false;
            
            $scope.personalizadaRecords = $rootScope.personalizadas;
            
            console.log($scope.personalizadas);
            
            $scope.colores = ["Blanco","Azul", "Amarillo","Verde", "Rosado", "Negro", "Morado"];
            
           
            $scope.createDecoracionPersonalizada = function () {
                $http.post('api/personalizada', {
                    color: $scope.data.colores,
                    peso: $scope.peso
                }).then(function (response) {
                    
                    {
                        $state.go('personalizadaList', {personalizadaId: response.data.id}, {reload: true});
                    };
                    
                     
                });
            };
             $scope.agregarAPersonalizada = function()
            {   
                
                $rootScope.personalizadas.push(
                        {
                            "id": response.data.id,
                            "foto":$scope.foto,
                            "color": $scope.data.colores,
                            "peso":$scope.peso
                            
                        });
                $state.go('personalizadaList', {}, {reload: true});

            };
        }
    ]);
}
)(angular);
