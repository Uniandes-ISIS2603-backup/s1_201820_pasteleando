(function(ng)
{
    var mod = ng.module('promocionModule', ['ui.router']);
     mod.constant("promocionContext", "api/promocion");
    mod.config(['$stateProvider', function($stateProvider)
        {
            var basePath = 'src/modules/promocion/';
            
            $stateProvider.state('promocion',
            {
                parent:'',
                url:'/promocion',
                  views:
                          {
                              'mainView':
                            {
                                templateUrl:basePath+'promocion.html',
                                controller:'promocionCtrl',
                                controllerAs:'ctrl'
                            }, 'listView@promocion':
                            {
                                templateUrl:basePath+'promocion.list.html',
                                
                            }
                          }
            }).state('promocion-create',{
                parent:'',
                url:'/create',      
                    views:
                            {
                               'createView':
                               {
                                   templateUrl:basePath+'create/promocion.create.html',
                                   controller:'promocionCreateCtrl',
                                   controllerAs:'ctrl'
                               }
                            }
            }).state('promocion-update',{
                parent:'',
                url:'/update/{promocionId:int}',
                param:{
                    promocionId:null
                },
                    views:
                            {
                                'updateView':
                                {
                                    templateUrl:basePath+'update/promocion.update.html',
                                    controller:'promocionUpdateCtrl',
                                    controllerAs:'ctrl'
                                }
                            }
            })
            
            
            
            ;
        }]);
    
})(window.angular);

