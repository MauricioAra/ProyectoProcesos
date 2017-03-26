(function() {
    'use strict';

    angular
        .module('proyectoProcesosApp')
        .controller('RiskDeleteController',RiskDeleteController);

    RiskDeleteController.$inject = ['$uibModalInstance', 'entity', 'Risk'];

    function RiskDeleteController($uibModalInstance, entity, Risk) {
        var vm = this;

        vm.risk = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Risk.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
