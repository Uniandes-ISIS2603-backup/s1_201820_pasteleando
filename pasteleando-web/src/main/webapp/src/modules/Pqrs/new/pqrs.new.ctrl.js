/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function (ng) 
{
    var mod = ng.module("pqrsModule");
    mod.constant("pqrsContext", "api/pqrs");
    mod.controller('pqrsNewCtrl', ['$scope', '$http', 'pqrsContext', '$state', '$rootScope',
 
        function ($scope, $http, pqrsContext, $state, $rootScope) 
        {
            $rootScope.edit = false;

            $scope.data = {};

            $scope.createPqrs = function () 
            {
                $http.post(pqrsContext, $scope.data).then(function (response) 
                {
                    $state.go('pqrsList', {pqrsId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)
(window.angular);