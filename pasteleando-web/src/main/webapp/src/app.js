(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
       'ui.router',
       'ui.bootstrap',
       
        // Internal modules dependencies       
        

        'facturaModule',  'loginModule','clienteModule',
        'personalizadaModule', 'carritoModule',
        'calificacionModule',
        
        'catalogoModule',
        'pedidoModule', 'pqrsModule',        
        'promocionModule'
        

        

    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
    
    app.run(['$rootScope', '$transitions', function ($rootScope, $transitions) {
            $transitions.onSuccess({to: '*'}, function (trans) {
                var $state = trans.router.stateService;
                var requireLogin = $state.current.data.requireLogin;
                var roles = $state.current.data.roles;

                /**
                 * @ngdoc function
                 * @name isAuthenticated
                 * @methodOf mainApp.module:mainApp
                 * @description Esta función define si el usuario se encuentra
                 * dentro de su cuenta.
                 * @returns {Boolean} Verdadero si está dentro de su cuenta.
                 */
                $rootScope.isAuthenticated = function () {
                    if (sessionStorage.getItem("name") !== null && sessionStorage.getItem("name") !== "undefined") {
                       
                        $rootScope.currentUser = sessionStorage.getItem("name");
                        $rootScope.idUser = sessionStorage.getItem("id");
                        return true;
                    } else {
                        return false;
                    }
                };
                
                /**
                 * @ngdoc function
                 * @name hasPermissions
                 * @methodOf mainApp.module:mainApp
                 * @description Esta función define si el usuario tiene permisos
                 * para acceder a la aplicación.
                 * @returns {Boolean} Verdadero si el usuario tiene permisos.
                 */
                $rootScope.hasPermissions = function () {
                    if (($rootScope.isAuthenticated) && (sessionStorage.getItem("rol") === "false")) {
                        return true;
                    } else {
                        return false;
                    }
                };
                if (requireLogin && (sessionStorage.getItem("name") === null)) {
                    event.preventDefault();
                    $state.go('login', $state.params);
                }
            });
            
            $rootScope.misPedidos = [];
            $rootScope.todosLosPedidos =[];
            $rootScope.personalizadas=[];
        }]);
})(window.angular);

