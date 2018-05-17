(function (ng) {
    var mod = ng.module("clienteModule");
    mod.constant("clienteContext", "api/clientes");
    mod.controller('clienteCtrl', ['$scope', '$http', 'clienteContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name editorials.controller:editorialCtrl
         * @description
         * Definición del controlador de Angular del módulo Editorial. 
         * Se crea el controlador con el cual se maneja el módulo.
         * En el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
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
        function ($scope, $http, clienteContext, $state, $rootScope) {  
            
            $scope.isVisible = false;
            
            $scope.mostrarFormasDePago = function()
            {
                $scope.isVisible = true             
            }
            
            $scope.tiposPagos = ["Tarjeta de credito" , "Tarjeta debito" , "PayPal"];
            
           
       
            
            /**
             * @ngdoc function
             * @name getEditorials
             * @methodOf editorials.controller:editorialCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentran las editoriales en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de las editoriales o API donde se puede consultar.
             */
            $http.get(clienteContext).then(function (response) {
               document.getElementById("nombreCliente").innerHTML = sessionStorage.name;
                console.log(response.data);
                $http.get(clienteContext+ "/" +sessionStorage.id).then(function(response2){
                   var puntos = response2.numeroPuntos;
                   
                   if(puntos === undefined)
                   {
                       puntos=0;
                   }
                   document.getElementById("puntos").innerHTML=puntos;
                   
                   $scope.data.push({
                      "puntos": puntos,
                      "nombre": response2.name
                   });
                   
        });
             
            });
            
             $scope.agregarFormaDePago = function()
            {
                console.log($rootScope.data.tipoPago);
                $http.put(clienteContext+ "/" +sessionStorage.id,$scope.data).then(function(response3){
                    $scope.aIterar = response3.data;
                    $state.reload();
                });
            }
        }
    ]);
}
)(window.angular);