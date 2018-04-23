/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) 
{
    var mod = ng.module("pqrsModule");
    mod.constant("pqrsContext", "api/pqrs");
    mod.controller('pqrsDeleteCtrl', ['$scope', '$http', 'pqrsContext', '$state',
        
        function ($scope, $http, pqrsContext, $state) 
        {
            var idPqrs = $state.params.pqrsId;
            
            $scope.deletePqrs = function () 
            {
                $http.delete(pqrsContext + '/' + idPqrs, {}).then(function (response) 
                {
                    $state.go('pqrsList', {pqrsId: response.data.id}, {reload: true});
                });
            };
        }
    ]); 
}
)
(window.angular);
