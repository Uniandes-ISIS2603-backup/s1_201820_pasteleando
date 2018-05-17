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

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/catalogo/';

            $urlRouterProvider.otherwise("/home");

            $stateProvider.state('catalogo', {
                url: '/catalogo',
                abstract: true,
                data: {
                    requireLogin: false,
                    roles: []
                }
                ,
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
                data: {
                    requireLogin: false,
                    roles: []
                }
                ,
                views: {
                    'listView': {
                        templateUrl: basePath + 'catalogo.list.html'
                    }
                }
            }).state('catalogoCreate', {
                url: '/catalogo/create',
                data: {
                    requireLogin: true,
                    roles: ["admin"]
                }
                ,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'create/catalogo.new.html',
                        controller: 'catalogoCreateCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            }) .state('catalogoDetail', {
                url: '/{catalogoId:int}/detail',
                parent: 'catalogo',
                param: {catalogoId: null},
                data: {
                    requireLogin: false,
                    roles: []
                }
                ,
                views: {
                    'listView': {
                        templateUrl: basePath + 'catalogo.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'catalogo.detail.html',
                        controller: 'catalogoDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
