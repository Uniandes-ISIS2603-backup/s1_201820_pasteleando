(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
       'ui.router',
       'ui.bootstrap',
       
        // Internal modules dependencies       
        'pasteleandoModule',

        'facturaModule',  'loginModule','clienteModule',
        'personalizadaModule', 'carritoModule'
        'facturaModule',  'loginModule','clienteModule','calificacionModule',
        'personalizadaModule'
        

        

    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);

