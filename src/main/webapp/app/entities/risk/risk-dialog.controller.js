(function() {
    'use strict';

    angular
        .module('proyectoProcesosApp')
        .controller('RiskDialogController', RiskDialogController);

    RiskDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Risk'];

    function RiskDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Risk) {
        var vm = this;

        vm.risk = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.risk.id !== null) {
                Risk.update(vm.risk, onSaveSuccess, onSaveError);
            } else {
                Risk.save(vm.risk, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('proyectoProcesosApp:riskUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
