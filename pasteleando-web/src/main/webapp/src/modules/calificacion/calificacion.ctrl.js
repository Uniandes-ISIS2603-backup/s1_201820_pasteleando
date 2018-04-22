(function(ng)
{
    var mod = ng.module('calificacionModule');
   mod.controller("calificacionCtrl", ['$scope', '$http','calificacionContext',
        function ($scope, $http, context) {
            $http.get('data/calificacion.json').then(function(response)
            {
                 $scope.calificacionRecords = response.data;
                 console.log($scope.calificacionRecords);
            })
           
        }
    ]);
})(window.angular);