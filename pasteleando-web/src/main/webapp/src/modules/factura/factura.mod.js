(function(ng)
{
    var mod = ng.module('facturaModule', ['ui.router']);
     mod.constant("facturaContext", "api/facturas");
    mod.config(['$stateProvider', function($stateProvider)
        {
            var basePath = 'src/modules/factura/';
            
            $stateProvider.state('facturas',
            {
                parent:'',
                url:'/facturas',
                data: {
                    requireLogin: true,
                    roles: ["admin", "cliente"]
                }
                ,
                  views:
                          {
                              'mainView':
                            {
                                templateUrl:basePath+'factura.html',
                                controller:'facturaCtrl',
                                controllerAs:'ctrl'
                            }, 'listView@facturas':
                            {
                                templateUrl:basePath+'factura.list.html',
                                
                            }
                          }
            }).state('home',{
                url:'/home',
                parent: '',
                data: {
                    requireLogin: false,
                    roles: []
                }
                ,
                views:{
                    'homeView':{
                        templateUrl: 'src/home.html'
                    }
                }
            }).state('facturas-create',{
                parent:'',
                url:'/createFactura',
                data: {
                    requireLogin: true,
                    roles: ["admin"]
                }
                ,
                    views:
                            {
                               'createView':
                               {
                                   templateUrl:basePath+'create/factura.create.html',
                                   controller:'facturaCreateCtrl',
                                   controllerAs:'ctrl'
                               }
                            }
            }).state('facturas-update',{
                parent:'',
                url:'/update/{facturaId:int}',
                param:{
                    facturaId:null
                },
                data: {
                    requireLogin: true,
                    roles: ["admin"]
                }
                ,
                    views:
                            {
                                'updateView':
                                {
                                    templateUrl:basePath+'update/factura.update.html',
                                    controller:'facturaUpdateCtrl',
                                    controllerAs:'ctrl'
                                }
                            }
            })
            
            
            
            ;
        }]);
    
})(window.angular);

