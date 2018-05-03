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
    var mod = ng.module("calificacionModule", ['ui.router']);
    mod.constant("calificacionContext", "api/calificaciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/calificacion/';
            
            $urlRouterProvider.otherwise("/home");
            $stateProvider.state('calificacion', {
                url: '/calificacion',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'calificacion.html',
                        controller: 'calificacionCtrl',
                        controllerAs: 'ctrl'
                    }
                }
    
            }).state('calificacionList', {
                url: '/list',
                parent: 'calificacion',
                views: {
                    'listView': {
                        templateUrl: basePath + 'calificacion.list.html'
                    }
                }
            }).state('calificacionDetail', {
                url: '/{calificacionId:int}/detail',
                parent: 'calificacion',
                param: {
                    calificacionId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'calificacion.list.html',
                        controller: 'calificacionDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'calificacion.detail.html',
                        controller: 'calificacionDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('calificacionCreate', {
                url: '/create',
                parent: 'calificacion',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/calificacion.new.html',
                        controller: 'calificacionNewCtrl'
                    }
                }
               
            }).state('calificacionUpdate', {
                url: '/update/{calificacionId:int}',
                parent: 'calificacion',
                param: {
                    calificacionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/calificacion.new.html',
                        controller: 'calificacionUpdateCtrl'
                    }
                }
                
            }).state('calificacionDelete', {
                url: '/delete/{calificacionId:int}',
                parent: 'calificacion',
                param: {
                    editorialId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/calificacion.delete.html',
                        controller: 'calificacionDeleteCtrl'
                    }
                }
               
            });
        }]);
})(window.angular);
