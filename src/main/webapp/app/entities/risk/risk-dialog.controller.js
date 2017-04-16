(function() {
    'use strict';

    angular
        .module('proyectoProcesosApp')
        .controller('RiskDialogController', RiskDialogController);

    RiskDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Risk', 'Project'];

    function RiskDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Risk, Project) {
        var vm = this;

        vm.risk = entity;
        vm.clear = clear;
        vm.save = save;
        vm.risk.projectId = $stateParams.id


        vm.impacts = [{name:"Insignificante",tag:1},{name:"Medio",tag:2},{name:"Grave",tag:3},{name:"Muy grave",tag:4},{name:"Catastrófico",tag:5}];
        vm.probabilities = [{name:"Frecuente",tag:1},{name:"Probable",tag:2},{name:"Posible",tag:3},{name:"Improbable",tag:4}];


        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            vm.risk.status = false;

            for(var i = 0; i < vm.impacts.length; i++){
                if(vm.impacts[i].tag == vm.impact){
                    vm.currentImpact = vm.impacts[i].name;
                }
            }
            for(var j = 0; j < vm.probabilities.length; j++){
                if(vm.probabilities[j].tag == vm.probability){
                    vm.currentProbability = vm.probabilities[j].name;
                }
            }
            vm.risk.probability = vm.currentProbability;
            vm.risk.impact = vm.currentImpact;
            Risk.save(vm.risk, onSaveSuccess, onSaveError);

        }

        function onSaveSuccess (result) {
            $scope.$emit('proyectoProcesosApp:riskUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.validateTag = function(){
            if(vm.impact != null){
                if(vm.probability == 1 && vm.impact == 1){
                    vm.risk.tag = "A1";
                }else if(vm.probability == 1 && vm.impact == 2){
                    vm.risk.tag = "A2";
                }else if(vm.probability == 1 && vm.impact == 3){
                    vm.risk.tag = "A3";
                }else if(vm.probability == 1 && vm.impact == 4){
                    vm.risk.tag = "A4";
                }else if(vm.probability == 1 && vm.impact == 5){
                    vm.risk.tag = "A5";
                }else if(vm.probability == 2 && vm.impact == 1){
                    vm.risk.tag = "B1";
                }else if(vm.probability == 2 && vm.impact == 2){
                    vm.risk.tag = "B2";
                }else if(vm.probability == 2 && vm.impact == 3){
                    vm.risk.tag = "B3";
                }else if(vm.probability == 2 && vm.impact == 4){
                    vm.risk.tag = "B4";
                }else if(vm.probability == 2 && vm.impact == 5){
                    vm.risk.tag = "B5";
                }else if(vm.probability == 3 && vm.impact == 1){
                    vm.risk.tag = "C1";
                }else if(vm.probability == 3 && vm.impact == 2){
                    vm.risk.tag = "C2";
                }else if(vm.probability == 3 && vm.impact == 3){
                    vm.risk.tag = "C3";
                }else if(vm.probability == 3 && vm.impact == 4){
                    vm.risk.tag = "C4";
                }else if(vm.probability == 3 && vm.impact == 5){
                    vm.risk.tag = "C5";
                }else if(vm.probability == 4 && vm.impact == 1){
                    vm.risk.tag = "D1";
                }else if(vm.probability == 4 && vm.impact == 2){
                    vm.risk.tag = "D2";
                }else if(vm.probability == 4 && vm.impact == 3){
                    vm.risk.tag = "D3";
                }else if(vm.probability == 4 && vm.impact == 4){
                    vm.risk.tag = "D4";
                }else if(vm.probability == 4 && vm.impact == 5){
                    vm.risk.tag = "D5";
                }
            }
        }







    }
})();
