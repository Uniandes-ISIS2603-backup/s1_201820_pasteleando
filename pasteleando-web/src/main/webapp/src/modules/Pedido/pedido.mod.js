/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function(ng)
{
     var mod = ng.module('pedidoModule', ['ui.router']);
     
     mod.constant("pedidoContext", "api/pedido");
     mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider)
         {
             var basePath = 'src/modules/Pedido/';
             
             $stateProvider.state('pedido',
             {
                 url:'/pedido',
                 data: {
                    requireLogin: true,
                    roles: ["admin", "cliente"]
                }
                ,
                   views:
                           {
                               mainView:
                             {
                                 templateUrl:basePath+'pedido.html',
                                 controller:'pedidoCtrl',
                                 controllerAs:'ctrl'
                             }
                           }
             }).state('pedidoList', {
                url: '/list',
                parent: 'pedido',
                data: {
                    requireLogin: true,
                    roles: ["admin", "cliente"]
                }
                ,
                views: {
                    'listView': {
                        templateUrl: basePath + 'pedido.list.html',
                        controller:'pedidoCtrl',
                        controllerAs:'ctrl'
                    }
                }
            }).state('createPedido', {
                url: '/new',
                parent:'',
                data: {
                    requireLogin: true,
                    roles: ["admin"]
                }
                ,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'new/pedido.new.html',
                        controller: 'pedidoNewCtrl',
                        controllerAs: 'ctrl'
                        
                    }
                }

            }) .state('pedidoDetail', {
                url: '/{pedidoId:int}/detail',
                parent: 'pedido',
                param: {pedidoId: null},
                data: {
                    requireLogin: true,
                    roles: ["admin", "cliente"]
                }
                ,
                views: {
                    'listView': {
                        templateUrl: basePath + 'pedido.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'pedido.detail.html',
                        controller: 'pedidoDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
         }
     ]);
     
 }) (window.angular);