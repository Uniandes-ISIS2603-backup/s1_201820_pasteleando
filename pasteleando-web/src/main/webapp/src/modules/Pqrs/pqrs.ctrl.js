/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function(ng)
{
     var mod = ng.module('pqrsModule');
     
     mod.constant("pqrsContext", "api/pqrs");
     mod.controller("pqrsCtrl", ['$scope', '$http','pqrsContext',
         
         function ($scope, $http, context) 
         {
             $http.get("data/pqrs.json").then(function(response)
             {
                  $scope.pqrsRecords = response.data;
             })
         }
     ]);
 })
 (window.angular);