/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function(ng)
{
    var mod = ng.module('pqrsModule', ['ui.router']);
    
    mod.constant("pqrsContext", "api/pqrs");
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider)
        {
            var basePath = 'src/modules/pqrs/';
            
            $stateProvider.state('pqrsList',
            {
                url:'/pqrs/list',
                  views:
                          {
                              mainView:
                            {
                                templateUrl:basePath+'pqrs.list.html',
                                controller:'pqrsCtrl',
                                controllerAs:'ctrl'
                            }
                          }
            });
        }]);
    
})
(window.angular);
