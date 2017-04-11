(function() {
    'use strict';

    angular
        .module('proyectoProcesosApp')
        .controller('TaskDialogController', TaskDialogController);

    TaskDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Task', 'Project'];

    function TaskDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Task, Project) {
        var vm = this;

        vm.task = entity;
        vm.clear = clear;
        vm.save = save;
        vm.projects = Project.query();
        vm.id = $stateParams.id;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            console.log(vm.task);

            vm.isSaving = true;
            if (vm.task.id !== null) {
                vm.task.projectId = vm.id;
                Task.update(vm.task, onSaveSuccess, onSaveError);
            } else {
                vm.task.projectId = vm.id;
                vm.task.status = false;
                vm.task.realHour = 0;
                Task.save(vm.task, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('proyectoProcesosApp:taskUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
