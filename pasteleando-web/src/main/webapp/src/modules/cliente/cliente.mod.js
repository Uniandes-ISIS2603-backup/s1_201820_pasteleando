/**
 * @ngdoc overview
 * @name editorials.module:editorialModule
 * @description
 * Definición del módulo de Angular de Editorial. El módulo encapsula todos los 
 * controladores y los templates HTML que estén relacionados con la Editorial 
 * directamente. En la configuración del módulo se injecta la dependencia de 
 * ui.router que es la que se utiliza para la configuración de las URLs bajo las
 * cuales se accede al módulo. Por ejemplo, para mostrar las editoriales en la 
 * URL: 'localhost:8080/editorials/list' es necesario configurar el router por 
 * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
 * un estado definido (estado de mostrar editoriales), el controlador y la vista 
 * correspondiente. Los estados definidos en este modulo son:
 * ```
 * | ESTADO           | URL                        | VISTAS                 |
 * |------------------|----------------------------|------------------------|
 * | editorials       | /editorials                | mainView:              |
 * |                  |                            | editorials.html        |
 * | editorialsList   | /list                      | listView:              |
 * |                  |                            | editorials.list.html   |
 * | editorialDetail  | /{editorialsId:int}/detail | listView:              |
 * |                  |                            | books.list.html        |
 * |                  |                            | detailView:            |
 * |                  |                            | editorials.detail.html |
 * | editorialsCreate | /create                    | detailView: (/new)     |
 * |                  |                            | /editorials.new.html   |
 * | editorialUpdate  | /update/{editorialId:int}  | detailView: (/new)     |
 * |                  |                            | /editorials.new.html   |
 * | editorialDelete  | /delete/{editorialId:int}  | detailView: (/delete)  |
 * |                  |                            | /editorials.delete.html|
 * |------------------|----------------------------|------------------------|
 *```
 */
(function (ng) {
    var mod = ng.module("clienteModule", ['ui.router']);
    mod.constant("clienteContext", "api/clientes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/cliente/';
            
            $urlRouterProvider.otherwise("/clienteList");
            $stateProvider.state('cliente', {
                url: '/cliente',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'cliente.html',
                        controller: 'clienteCtrl',
                        controllerAs: 'ctrl'
                    }
                }
    
            }).state('clienteList', {
                url: '/list',
                parent: 'cliente',
                views: {
                    'listView': {
                        templateUrl: basePath + 'cliente.list.html'
                    }
                }
            }).state('clienteDetail', {
                url: '/{clienteId:int}/detail',
                parent: 'cliente',
                param: {
                    calificacionId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'cliente.list.html',
                        controller: 'clienteDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'cliente.detail.html',
                        controller: 'clienteDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('clienteCreate', {
                url: '/create',
                parent: 'cliente',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/cliente.new.html',
                        controller: 'clienteNewCtrl'
                    }
                }
               
            }).state('clienteUpdate', {
                url: '/update/{clienteId:int}',
                parent: 'cliente',
                param: {
                    clienteId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/cliente.new.html',
                        controller: 'clienteUpdateCtrl'
                    }
                }
                
            }).state('clienteDelete', {
                url: '/delete/{clienteId:int}',
                parent: 'cliente',
                param: {
                    editorialId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/cliente.delete.html',
                        controller: 'clienteDeleteCtrl'
                    }
                }
               
            });
        }]);
})(window.angular);
