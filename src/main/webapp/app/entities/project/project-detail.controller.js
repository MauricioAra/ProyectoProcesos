(function() {
    'use strict';

    angular
        .module('proyectoProcesosApp')
        .controller('ProjectDetailController', ProjectDetailController);

    ProjectDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Project', 'Company', 'Task', 'Risk','$http','$mdDialog','ProjectFactory','$mdToast'];

    function ProjectDetailController($scope, $rootScope, $stateParams, previousState, entity, Project, Company, Task, Risk,$http,$mdDialog,ProjectFactory,$mdToast) {
        var vm = this;

        vm.project = entity;

        vm.previousState = previousState.name;

        vm.gridOptions = {};
        vm.gridOptions.data = [];
        vm.gridOptions.columnDefs = [];

        vm.uno = false;
        vm.dos = false;
        vm.tres = false;
        vm.cuatro = false;
        vm.cinco = false;

        var unsubscribe = $rootScope.$on('proyectoProcesosApp:projectUpdate', function(event, result) {
            vm.project = result;
        });
        $scope.$on('$destroy', unsubscribe);

        function init(){
            vm.uno = true;
            vm.dos = true;
            vm.tres = true;
            vm.cuatro = true;
            getRisks();
            getTask();
            getRisksMatrix();
            getEV();
        }
        init();

        function getTask(){
            ProjectFactory.getTask(vm.project.id)
                .success(function(data){
                    vm.tasks = data;
                    vm.dos = false;
                });
        }

        function getEV(){
            ProjectFactory.getEV(vm.project.id)
                .success(function(data){
                    vm.results = data;
                    vm.cuatro = false;
                });
        }

        function getRisks(){
            ProjectFactory.getRisk(vm.project.id)
                .success(function(data){
                    vm.risks = data;
                    vm.uno = false;
                });
        }

        function getRisksMatrix(){
            ProjectFactory.getMatrix(vm.project.id)
                .success(function(data){
                    vm.matrix = data;
                    vm.tres = false;
                });
        }

        $scope.$on('proyectoProcesosApp:riskUpdate', function(){
            getRisks();
        });

        $scope.$on('proyectoProcesosApp:taskUpdate', function(){
            getTask();
        });


        vm.deleteRisk = function(id){
            Risk.delete({id: id},
                function () {
                    getRisks();
                });
        }

        vm.deleteTask = function(id){
            Task.delete({id: id},
                function () {
                    getTask();
                });
        }

        vm.completed = function(ev,id) {
            // Appending dialog to document.body to cover sidenav in docs app
            var confirm = $mdDialog.prompt()
                .title('¿Terminaste la tarea?')
                .textContent('Por favor ingresa las horas que se invirtieron en la tarea.')
                .placeholder('Horas: ejemplo 1')
                .ariaLabel('Dog name')
                .initialValue('')
                .targetEvent(ev)
                .ok('Guardar')
                .cancel('cancelar');

            $mdDialog.show(confirm).then(function(result) {
                var realHour = parseFloat(result);

                ProjectFactory.finishTask(id,realHour)
                    .success(function(data){
                        init();
                    });
            }, function() {
                init();
            });
        };

        $scope.read = function (workbook) {
            var tempList = [];
            vm.cinco = true;
            for(var i = 0; i < workbook.length; i++ ){
                var tempObject = {};
                tempObject.id = "";
                tempObject.name = workbook[i].NOMBRE;
                tempObject.description = workbook[i].DESCRIPCION;
                tempObject.cost = workbook[i].COSTO;
                tempObject.time = workbook[i].HORAS;
                tempObject.realHour = 0;
                tempObject.status = false;
                tempObject.projectId = vm.project.id;
                tempList.push(tempObject);
            }

            ProjectFactory.upload(tempList)
                .success(function(data){
                    init();
                    vm.cinco = false;
                    $mdToast.show(
                        $mdToast.simple()
                            .textContent('Se registró correctamente')
                            .position('top','left' )
                            .hideDelay(3000)
                    );
                });

        }

        vm.details = function(ev,object){
            ProjectFactory.setCurrentRisk(object);
            $mdDialog.show({
                controller: DialogController,
                templateUrl: 'app/entities/project/projectRisk.tpl.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose:true,
                fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
            })
                .then(function(answer) {
                    $scope.status = 'You said the information was "' + answer + '".';
                }, function() {
                    $scope.status = 'You cancelled the dialog.';
                });

        }





        $scope.limitOptions = [5, 10, 15];
        $scope.options = {
            rowSelection: true,
            multiSelect: true,
            autoSelect: true,
            decapitate: false,
            largeEditDialog: false,
            boundaryLinks: false,
            limitSelect: true,
            pageSelect: true
        };
        $scope.query = {
            order: 'name',
            limit: 5,
            page: 1
        };
        $scope.toggleLimitOptions = function () {
            $scope.limitOptions = $scope.limitOptions ? undefined : [5, 10, 15];
        };

        // ---------------
        $scope.limitOptionsR = [5, 10, 15];
        $scope.optionsR = {
            rowSelection: true,
            multiSelect: true,
            autoSelect: true,
            decapitate: false,
            largeEditDialog: false,
            boundaryLinks: false,
            limitSelect: true,
            pageSelect: true
        };
        $scope.queryR = {
            order: 'name',
            limit: 5,
            page: 1
        };
        $scope.toggleLimitOptions = function () {
            $scope.limitOptions = $scope.limitOptions ? undefined : [5, 10, 15];
        };

    }

    DialogController.$inject = ['$scope','$mdDialog','$rootScope','ProjectFactory'];

    function DialogController($scope, $mdDialog,$rootScope,ProjectFactory) {
        var vm = this;

        function init(){
            $scope.risk = ProjectFactory.getCurrentRisk();
        }
        init();

        $scope.cancel = function() {
            $mdDialog.hide();
        };


    }

})();
