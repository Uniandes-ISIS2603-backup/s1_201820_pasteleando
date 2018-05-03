(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.controller('catalogoDeleteCtrl', ['$scope', '$http', 'catalogoContext', '$state',
        /**
         * @ngdoc controller
         * @name catalogo.controller:catalogoDeleteCtrl
         * @description
         * Definición del controlador auxiliar para eliminar decoraciones. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} catalogoContext Constante injectada que contiene la ruta
         * donde se encuentra el API de catálogo en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, catalogoContext, $state) {
            var idcatalogo = $state.params.catalogoId;
            /**
             * @ngdoc function
             * @name deleteDecoracionCatalogo
             * @methodOf catalogo.controller:catalogoDeleteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para eliminar la decoración.
             * @param {String} id El ID de la decoración a eliminar.
             */
            $scope.deletecatalogo = function () {
                $http.delete(catalogoContext + '/' + idcatalogo, {}).then(function (response) {
                    $state.go('catalogoList', {catalogoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);