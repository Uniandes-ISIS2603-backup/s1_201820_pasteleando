(function (ng) {
    var mod = ng.module("clienteModule");
    mod.constant("clienteContext", "api/clientes");
    
    mod.controller('clienteNewCtrl', ['$scope', '$http', 'clienteContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name editorials.controller:editorialNewCtrl
         * @description
         * Definición del controlador auxiliar para crear Editoriales. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} editorialContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Editoriales en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} $rootScope Referencia injectada al Scope definida para
         * toda la aplicación.
         */
        
        
        function ($scope, $http, clienteContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};


            $http.get('api/clientes').then(function (response) {
                $scope.users = response.data;
            });
            
            /**
             * @ngdoc function
             * @name createEditorial
             * @methodOf editorials.controller:editorialNewCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear la editorial.
             * @param {Object} editorial Objeto con la nueva de la editorial.
             */
            
            $scope.opcionesPago = ["Efectivo"];
            
            
            
            $scope.createcliente = function () {
                $scope.data.tipoUsuario = 'false';
                var si=false;
                  for (var item in $scope.users) 
                      {
                      if($scope.users[item].name === $scope.data.name)
                      {
                          
                      si = true;
                      
                      }
                      else{
                          $http.post(clienteContext, $scope.data).then(function (response) {
                    $state.go('clienteList', {clienteId: response.data.id}, {reload: true})
                  });
                    }
                
            }
        }
        
        }
        
    ]);
    
    
    
    
    
   }

)(window.angular);