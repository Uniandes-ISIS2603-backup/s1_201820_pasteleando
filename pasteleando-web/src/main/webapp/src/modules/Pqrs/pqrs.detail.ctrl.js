/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) 
{
    var mod = ng.module("pqrsModule");
    mod.constant("pqrsContext", "api/pqrs");
    mod.controller('pqrsDetailCtrl', ['$scope', '$http', 'pqrsContext', '$state', '$filter',
        
        function ($scope, $http, pqrsContext, $state, $filter) 
        {
            if (($state.params.pqrsId !== undefined) && ($state.params.pqrsId !== null)) 
            {
               $http.get(pqrsContext + '/' + $state.params.pqrsId).then(function (response) 
                {
                    $scope.currentPqrs = response.data; 
                });
            }
        }
    ]);
}
)
(window.angular);