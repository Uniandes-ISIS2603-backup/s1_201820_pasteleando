(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogos");
    mod.controller('catalogoNewCtrl', ['$scope', '$http', 'catalogoContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name catalogo.controller:catalogoNewCtrl
         * @description
         * Definición del controlador auxiliar para crear decoraciones. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} editorialContext Constante injectada que contiene la ruta
         * donde se encuentra el API del catálogo en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} $rootScope Referencia injectada al Scope definida para
         * toda la aplicación.
         */
        function ($scope, $http, catalogoContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};

            /**
             * @ngdoc function
             * @name createDecoraciónCatalogo
             * @methodOf catalogo.controller:catalogoNewCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear la decoración.
             * @param {Object} catalogo Objeto con la nueva de la decoración.
             */
            $scope.createcatalogo = function () {
                $http.post(catalogoContext, $scope.data).then(function (response) {
                    $state.go('catalogoList', {catalogoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);