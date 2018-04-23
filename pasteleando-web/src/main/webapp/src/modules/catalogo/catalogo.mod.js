/**
 * @ngdoc overview
 * @name catalogo.module:catalogoModule
 * @description
 * Definición del módulo de Angular de Catálogo. El módulo encapsula todos los 
 * controladores y los templates HTML que estén relacionados con el catálogo 
 * directamente. En la configuración del módulo se injecta la dependencia de 
 * ui.router que es la que se utiliza para la configuración de las URLs bajo las
 * cuales se accede al módulo. Por ejemplo, para mostrar las decoraciones del catálogo en la 
 * URL: 'localhost:8080/catalogo/list' es necesario configurar el router por 
 * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
 * un estado definido (estado de mostrar catálogo), el controlador y la vista 
 * correspondiente. Los estados definidos en este modulo son:
 * ```
 * | ESTADO           | URL                        | VISTAS                 |
 * |------------------|----------------------------|------------------------|
 * | catalogo         | /catalogo                  | mainView:              |
 * |                  |                            | catalogo.html          |
 * | catalogoList     | /list                      | listView:              |
 * |                  |                            | catalogo.list.html     |
 * | catalogoDetail   | /{catalogoId:int}/detail   | listView:              |
 * |                  |                            | catalogo.list.html     |
 * |                  |                            | detailView:            |
 * |                  |                            | catalogo.detail.html   |
 * | catalogoCreate   | /create                    | detailView: (/new)     |
 * |                  |                            | /catalogo.new.html     |
 * | catalogoUpdate   | /update/{catalogoId:int}   | detailView: (/new)     |
 * |                  |                            | /catalogo.new.html     |
 * | catalogoDelete   | /delete/{catalogoId:int}   | detailView: (/delete)  |
 * |                  |                            | /catalogo.delete.html  |
 * |------------------|----------------------------|------------------------|
 *```
 */
(function (ng) {
    var mod = ng.module("catalogoModule", ['ui.router']);
    mod.constant("catalogoContext", "api/catalogo");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/catalogo/';
            
            $urlRouterProvider.otherwise("/catalogoList");
            $stateProvider.state('catalogo', {
                url: '/catalogo',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'catalogo.html',
                        controller: 'catalogoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
    
            }).state('catalogoList', {
                url: '/list',
                parent: 'catalogo',
                views: {
                    'listView': {
                        templateUrl: basePath + 'catalogo.list.html'
                    }
                }
            }).state('catalogoDetail', {
                url: '/{catalogoId:int}/detail',
                parent: 'catalogo',
                param: {
                    calificacionId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'catalogo.list.html',
                        controller: 'catalogoDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'catalogo.detail.html',
                        controller: 'catalogoDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('catalogoCreate', {
                url: '/create',
                parent: 'catalogo',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/catalogo.new.html',
                        controller: 'catalogoNewCtrl'
                    }
                }
               
            }).state('catalogoUpdate', {
                url: '/update/{catalogoId:int}',
                parent: 'catalogo',
                param: {
                    catalogoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/catalogo.new.html',
                        controller: 'catalogoUpdateCtrl'
                    }
                }
                
            }).state('catalogoDelete', {
                url: '/delete/{catalogoId:int}',
                parent: 'catalogo',
                param: {
                    editorialId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/catalogo.delete.html',
                        controller: 'catalogoDeleteCtrl'
                    }
                }
               
            });
        }]);
})(window.angular);
