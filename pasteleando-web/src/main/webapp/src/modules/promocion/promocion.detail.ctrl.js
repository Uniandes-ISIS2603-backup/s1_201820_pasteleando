(function (ng) {
    var mod = ng.module("promocionModule");
    mod.constant("promocionContext", "api/catalogo/1000/promociones/100");
    mod.controller('promocionDetailCtrl', ['$scope', '$http', 'promocionContext', '$state',
        /**
         * @ngdoc controller
         * @name editorials.controller:editorialDetailCtrl
         * @description
         * Definición de un controlador auxiliar del módulo Editorial. 
         * Se crea el controlador con el cual se manejan las vistas de detalle
         * del módulo.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} editorialContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Editoriales en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, promocionContext, $state) {
            if (($state.params.promocionId !== undefined)&& ($state.params.promocionId !== null)) {
             /**
             * @ngdoc function
             * @name getEditorialID
             * @methodOf editorials.controller:editorialDetailCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentra la editorial por ID en formato JSON.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de la editorial o API donde se puede consultar.
             */
                $http.get(promocionContext + '/' + $state.params.promocionId).then(function (response) {
//                    $scope.promocionRecords = response.data.books;
                    $scope.currentpromocion = response.data;
                });
            }
        }
    ]);
}
)(window.angular);