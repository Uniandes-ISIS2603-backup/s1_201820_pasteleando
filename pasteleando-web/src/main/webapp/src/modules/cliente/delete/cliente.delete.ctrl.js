(function (ng) {
    var mod = ng.module("clienteModule");
    mod.constant("clienteContext", "api/cliente");
    mod.controller('clienteDeleteCtrl', ['$scope', '$http', 'clienteContext', '$state',
        /**
         * @ngdoc controller
         * @name editorials.controller:editorialDeleteCtrl
         * @description
         * Definición del controlador auxiliar para eliminar Editoriales. 
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
        function ($scope, $http, clienteContext, $state) {
            var idcliente = $state.params.clienteId;
            /**
             * @ngdoc function
             * @name deleteEditorial
             * @methodOf editorials.controller:editorialDeleteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para eliminar la editorial.
             * @param {String} id El ID de la editorial a eliminar.
             */
            $scope.deletecliente = function () {
                $http.delete(clienteContext + '/' + idcliente, {}).then(function (response) {
                    $state.go('clienteList', {clienteId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);