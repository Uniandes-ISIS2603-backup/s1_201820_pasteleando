(function(ng)
{
    var mod = ng.module('promocionModule');
   mod.controller("promocionCtrl", ['$scope', '$http','promocionContext', '$state',
        function ($scope, $http, context,$state) {
            var vm = this;
            var idpromocion = $state.params.id;
            $http.get(context).then(function(response)
            {
                 $scope.promocionsRecords = response.data;
                 vm.promocions = $scope.promocionsRecords;
                 for(var i = 0; i< vm.promocions.length;i++){
                     vm.promocions[i].fecha = vm.promocions[i].fecha.split("T")[0];
                 }
            })
            $scope.eliminarpromocion = function(promocionId)
            {
                     $http.delete(context + '/' + promocionId, {}).then(function (response)
                     {
                     $state.reload();
                });
            };
        }
    ]);
})(window.angular); 