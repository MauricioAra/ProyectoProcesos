(function() {
    'use strict';

    angular
        .module('proyectoProcesosApp')
        .controller('ProjectDetailController', ProjectDetailController);

    ProjectDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Project', 'Company', 'Task', 'Risk'];

    function ProjectDetailController($scope, $rootScope, $stateParams, previousState, entity, Project, Company, Task, Risk) {
        var vm = this;

        vm.project = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('proyectoProcesosApp:projectUpdate', function(event, result) {
            vm.project = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
