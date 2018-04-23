(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    
    mod.controller('catalogoUpdateCtrl', ['$scope', '$http', 'catalogoContext', '$state',  '$rootScope',
        /**
         * @ngdoc controller
         * @name catalogo.controller:catalogoUpdateCtrl
         * @description
         * Definición del controlador auxiliar para actualizar decoraciones del catálogo. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} catalogoContext Constante injectada que contiene la ruta
         * donde se encuentra el API del catálogo en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, catalogoContext, $state,  $rootScope) {
            $rootScope.edit = true;

            $scope.data = {};

            var idcatalogo = $state.params.catalogoId;

            /**
             * @ngdoc function
             * @name getDecoracionCatalogo
             * @methodOf catalogo.controller:catalogoUpdateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentra la decoración por ID en formato JSON.
             * @param {String} URL Dirección donde se encuentra el recurso
             * del catálogo o API donde se puede consultar.
             */
            $http.get(catalogoContext + '/' + idcatalogo).then(function (response) {
                var catalogo = response.data;
                $scope.data.categoria = catalogo.categoria;
            });

            /**
             * @ngdoc function
             * @name createDecoracionCatalogo
             * @methodOf catalogo.controller:catalogoUpdateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para actualizar la decoración
             * por ID en formato JSON.
             * @param {String} id El ID de la decoración del catálogo a actualizar.
             * @param {Object} catalogo Objeto con la información nueva del catálogo.
             */
            $scope.createcatalogo = function () {
                $http.put(catalogoContext + "/" + idcatalogo, $scope.data).then(function (response) {
                    $state.go('catalogoList', {catalogoId: response.data.id}, {reload: true});
                });
            }
        }]);
}
)(window.angular);