(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.controller('catalogoDetailCtrl', ['$scope', '$http', 'catalogoContext', '$state',
        /**
         * @ngdoc controller
         * @name catalogo.controller:catalogoDetailCtrl
         * @description
         * Definición de un controlador auxiliar del módulo Catálogo. 
         * Se crea el controlador con el cual se manejan las vistas de detalle
         * del módulo.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} editorialContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Decoración Catálogo en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, catalogoContext, $state) {
            if (($state.params.catalogoId !== undefined)&& ($state.params.catalogoId !== null)) {
             /**
             * @ngdoc function
             * @name getDecoracionCatalogo
             * @methodOf catalogo.controller:catalogoDetailCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentra la editorial por ID en formato JSON.
             * @param {String} URL Dirección donde se encuentra el recurso
             * del catálogo o API donde se puede consultar.
             */
                $http.get(catalogoContext + '/' + $state.params.catalogoId).then(function (response) {
                    $scope.currentcatalogo = response.data;
                });
            }
        }
    ]);
}
)(window.angular);