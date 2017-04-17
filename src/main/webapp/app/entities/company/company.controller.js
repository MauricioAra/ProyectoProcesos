(function() {
    'use strict';

    angular
        .module('proyectoProcesosApp')
        .controller('CompanyController', CompanyController);

    CompanyController.$inject = ['Company'];

    function CompanyController(Company) {

        var vm = this;
        var originatorEv;

        vm.openMenu = function($mdOpenMenu, ev) {
            originatorEv = ev;
            $mdOpenMenu(ev);
        };

        vm.companies = [];
        vm.areCompanies = false;

        loadAll();

        function loadAll() {
            Company.query(function(result) {
                vm.companies = result;
                vm.areCompanies = true;
                vm.searchQuery = null;
            });
        }
    }
})();
