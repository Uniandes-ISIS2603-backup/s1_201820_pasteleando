(function (ng) {
    var mod = ng.module("catalogoModule");
    mod.constant("catalogoContext", "api/catalogo");
    mod.controller('catalogoCtrl', ['$scope', '$http', 'catalogoContext', '$state',
        /**
         * @ngdoc controller
         * @name catalogo.controller:catalogoCtrl
         * @description
         * Definición del controlador de Angular del módulo Catálogo. 
         * Se crea el controlador con el cual se maneja el módulo.
         * En el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} editorialContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Catalogo en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, catalogoContext, $state) {
            /**
             * @ngdoc function
             * @name getDecoracionesCatalogo
             * @methodOf catalogo.controller:catalogoCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentran las editoriales en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de las decoraciones del catálogo o API donde se puede consultar.
             */
            $http.get("catalogoContext").then(function (response) {
                $scope.catalogoRecords = response.data;
            });
        }
    ]);
}
)(window.angular);