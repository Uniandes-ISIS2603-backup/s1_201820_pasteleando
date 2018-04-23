(function (ng) {
    var mod = ng.module("calificacionModule");
    mod.constant("calificacionContext", "api/calificacion");
    
    mod.controller('calificacionUpdateCtrl', ['$scope', '$http', 'calificacionContext', '$state',  '$rootScope',
        /**
         * @ngdoc controller
         * @name editorials.controller:editorialUpdateCtrl
         * @description
         * Definición del controlador auxiliar para actualizar Editoriales. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} editorialContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Editoriales en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} booksContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Libros en el Backend.
         */
        function ($scope, $http, calificacionContext, $state,  $rootScope) {
            $rootScope.edit = true;

            $scope.data = {};

            var idcalificacion = $state.params.calificacionId;

            /**
             * @ngdoc function
             * @name getEditorialID
             * @methodOf editorials.controller:editorialUpdateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentra la editorial por ID en formato JSON.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de la editorial o API donde se puede consultar.
             */
            $http.get(calificacionContext + '/' + idcalificacion).then(function (response) {
                var calificacion = response.data;
                $scope.data.name = calificacion.name;
            });

            /**
             * @ngdoc function
             * @name createEditorial
             * @methodOf editorials.controller:editorialUpdateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para actualizar la editorial 
             * por ID en formato JSON.
             * @param {String} id El ID de la editorial a actualizar.
             * @param {Object} editorial Objeto con la información nueva de la editorial.
             */
            $scope.createcalificacion = function () {
                $http.put(calificacionContext + "/" + idcalificacion, $scope.data).then(function (response) {
                    $state.go('calificacionList', {calificacionId: response.data.id}, {reload: true});
                });
            }
        }]);
}
)(window.angular);