(function (ng) {
    var mod = ng.module("loginModule");
    mod.controller('loginCtrl', ['$scope', '$http', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name login.controller:loginCtrl
         * @description
         * Definición del controlador de Angular del módulo Login. 
         * Se crea el controlador con el cual se maneja el módulo.
         * En el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} $rootScope Referencia injectada al Scope definido
         * para toda la aplicación.
         */
        function ($scope, $http, $state, $rootScope) {

            $scope.user = {};
            $scope.data = {};
            
            $http.get('api/clientes').then(function (response) {
                $scope.users = response.data;
            });

            /**
             * @ngdoc function
             * @name autenticar
             * @methodOf login.controller:loginCtrl
             * @description
             * Esta función procesa el inicio de sesión usando los datos del
             * $scope.
             */
            $scope.autenticar = function () {
                        
                
                var flag = false;
                $rootScope.loginResponse = {};
                $rootScope.loginResponse.name = $scope.name;
                $rootScope.loginResponse.clave = $scope.clave;
                $scope.idUsuario = "";
                 if($scope.tipoUsuario == "false")
                {
                    $scope.tipoUsuario = false;
                }
                else
                {
                    $scope.tipoUsuario = true;
                }
                $rootScope.loginResponse.tipoUsuario = $scope.tipoUsuario;
               
                for (var item in $scope.users) {
                    if ($scope.users[item].name == $rootScope.loginResponse.name && $scope.users[item].clave == $rootScope.loginResponse.clave) {
                        flag = true;
                        $scope.user = $scope.users[item];
                        $state.go('home', {}, {reload: true});
                        $scope.idUsuario = $scope.users[item].id;
                        for(var it in $rootScope.todosLosPedidos)
                        {
                            console.log($rootScope.todosLosPedidos[it].idCliente);
                            console.log($scope.idUsuario);
                            if($rootScope.todosLosPedidos[it].idCliente == $scope.idUsuario)
                            {
                                $rootScope.misPedidos.push($rootScope.todosLosPedidos[it]);
                            }
                        }
                        console.log($rootScope.misPedidos);
                        console.log($rootScope.todosLosPedidos);
                        break;
                    }
                }
                if (!flag) {
                    $rootScope.alerts.push({type: "danger", msg: "Incorrect username or password."});
                } else {
                    sessionStorage.setItem("name", $scope.name);
                    sessionStorage.setItem("id",$scope.idUsuario);
                    sessionStorage.setItem("rol", $scope.tipoUsuario);
                    $rootScope.currentUser = $scope.name; 
                    console.log(sessionStorage);
                }
                
            };
        }
    ]);
}
)(window.angular);
