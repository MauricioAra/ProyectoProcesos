(function() {
    'use strict';

    angular
        .module('proyectoProcesosApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('risk', {
            parent: 'entity',
            url: '/risk',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyectoProcesosApp.risk.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/risk/risks.html',
                    controller: 'RiskController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('risk');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('risk-detail', {
            parent: 'risk',
            url: '/risk/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'proyectoProcesosApp.risk.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/risk/risk-detail.html',
                    controller: 'RiskDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('risk');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Risk', function($stateParams, Risk) {
                    return Risk.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'risk',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('risk-detail.edit', {
            parent: 'risk-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/risk/risk-dialog.html',
                    controller: 'RiskDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Risk', function(Risk) {
                            return Risk.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('risk.new', {
            parent: 'risk',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/risk/risk-dialog.html',
                    controller: 'RiskDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                name: null,
                                tag: null,
                                probability: null,
                                impact: null,
                                status: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('risk', null, { reload: 'risk' });
                }, function() {
                    $state.go('risk');
                });
            }]
        })
        .state('risk.edit', {
            parent: 'risk',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/risk/risk-dialog.html',
                    controller: 'RiskDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Risk', function(Risk) {
                            return Risk.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('risk', null, { reload: 'risk' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('risk.delete', {
            parent: 'risk',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/risk/risk-delete-dialog.html',
                    controller: 'RiskDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Risk', function(Risk) {
                            return Risk.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('risk', null, { reload: 'risk' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
