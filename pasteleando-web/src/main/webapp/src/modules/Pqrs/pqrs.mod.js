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
             var basePath = 'src/modules/Pqrs/';
             
             $stateProvider.state('pqrsList',
             {
                 url:'/pqrs/list',
                 data: {
                    requireLogin: false,
                    roles: []
                }
                ,
                   views:
                           {
                               mainView:
                             {
                                 templateUrl:basePath+'pqrs.list.html',
                                 controller:'pqrsCtrl',
                                 controllerAs:'ctrl'
                             }
                           }
             })
                     .state('pqrsCreate',
             {
                url:'/pqrs/create',
                data: {
                    requireLogin: true,
                    roles: ["cliente"]
                }
                ,
                views:
                        {
                            createPqrs:
                            {
                                templateUrl:basePath + 'new/pqrs.new.html',
                                controller:'pqrsNewCtrl',
                                controllerAs:'ctrl'
                            }
                        }
             })
         }]);
     
 })
 (window.angular);