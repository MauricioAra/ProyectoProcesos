(function() {
    'use strict';

    angular
        .module('proyectoProcesosApp')
        .controller('RiskDetailController', RiskDetailController);

    RiskDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Risk', 'Project'];

    function RiskDetailController($scope, $rootScope, $stateParams, previousState, entity, Risk, Project) {
        var vm = this;

        vm.risk = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('proyectoProcesosApp:riskUpdate', function(event, result) {
            vm.risk = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
