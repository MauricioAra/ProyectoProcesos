(function() {
    'use strict';

    angular
        .module('proyectoProcesosApp')
        .controller('CompanyDetailController', CompanyDetailController);

    CompanyDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Company', 'Project','$http'];

    function CompanyDetailController($scope, $rootScope, $stateParams, previousState, entity, Company, Project,$http) {
        var vm = this;
        vm.gridOptions = {};
        vm.gridOptions.data = [];
        vm.gridOptions.columnDefs = []
        vm.company = entity;
        vm.previousState = previousState.name;
        vm.areProjects = false;
        var originatorEv;
        vm.openMenu = function($mdOpenMenu, ev) {
            originatorEv = ev;
            $mdOpenMenu(ev);
        };

        function init(){
            $http.get('http://localhost:9000/api/companies/projects/'+ vm.company.id)
                .success(function(data){
                    vm.areProjects = true;
                    vm.projects = data;
                });
        }
        init();

        $rootScope.$on('projectSaved',function(){
            init();
        });

        vm.deleteProject = function(id){
            Project.delete({id: id},
                function () {
                    init();
                });
        }

        var unsubscribe = $rootScope.$on('proyectoProcesosApp:companyUpdate', function(event, result) {
            vm.company = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
