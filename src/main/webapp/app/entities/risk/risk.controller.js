(function() {
    'use strict';

    angular
        .module('proyectoProcesosApp')
        .controller('RiskController', RiskController);

    RiskController.$inject = ['Risk'];

    function RiskController(Risk) {

        var vm = this;

        vm.risks = [];

        loadAll();

        function loadAll() {
            Risk.query(function(result) {
                vm.risks = result;
                vm.searchQuery = null;
            });
        }
    }
})();
