(function (ng) {
    var mod = ng.module("calificacionModule");
    mod.constant("calificacionContext", "api/calificaciones");
    mod.controller('calificacionDetailCtrl', ['$scope', '$http', 'calificacionContext', '$state',
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
        function ($scope, $http, calificacionContext, $state) {
            if (($state.params.calificacionId !== undefined)&& ($state.params.calificacionId !== null)) {
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
                $http.get(calificacionContext + '/' + $state.params.calificacionId).then(function (response) {
//                    $scope.clienteRecords = response.data.books;
                    $scope.currentcalificacion = response.data;
                });
            }
        }
    ]);
}
)(window.angular);