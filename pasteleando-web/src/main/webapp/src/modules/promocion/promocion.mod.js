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
    var mod = ng.module("promocionModule", ['ui.router']);
    mod.constant("promocionContext", "api/promocion");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/promocion/';
            
            $urlRouterProvider.otherwise("/home");
            $stateProvider.state('promocion', {
                url: '/promocion',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'promocion.html',
                        controller: 'promocionCtrl',
                        controllerAs: 'ctrl'
                    }
                }
    
            }).state('promocionList', {
                url: '/list',
                parent: 'promocion',
                views: {
                    'listView': {
                        templateUrl: basePath + 'promocion.list.html'
                    }
                }
            }).state('promocionDetail', {
                url: '/{promocionId:int}/detail',
                parent: 'promocion',
                param: {
                    calificacionId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'promocion.list.html',
                        controller: 'promocionDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'promocion.detail.html',
                        controller: 'promocionDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('promocionCreate', {
                url: '/create',
                parent: 'promocion',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/promocion.new.html',
                        controller: 'promocionNewCtrl'
                    }
                }
               
            }).state('promocionUpdate', {
                url: '/update/{promocionId:int}',
                parent: 'promocion',
                param: {
                    promocionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/promocion.new.html',
                        controller: 'promocionUpdateCtrl'
                    }
                }
                
            }).state('promocionDelete', {
                url: '/delete/{promocionId:int}',
                parent: 'promocion',
                param: {
                    editorialId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/promocion.delete.html',
                        controller: 'promocionDeleteCtrl'
                    }
                }
               
            });
        }]);
})(window.angular);
