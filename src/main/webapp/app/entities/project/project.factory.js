(function() {
    'use strict';

    angular
        .module('proyectoProcesosApp')
        .service('ProjectFactory', ProjectFactory);
    ProjectFactory.$inject = ['$http'];
    /** @ngInject */
    function ProjectFactory($http) {

        var currentRisk = {};
        var service = {
           getTask:getTask,
            getEV:getEV,
            getRisk:getRisk,
            getMatrix:getMatrix,
            finishTask:finishTask,
            upload:upload,
            setCurrentRisk:setCurrentRisk,
            getCurrentRisk:getCurrentRisk

        };
        return service;

        function getTask(id){
            return $http.get('http://localhost:9000/api/tasks/project/'+ id);
        }

        function getEV(id){
            return $http.post('http://localhost:9000/api/projects/calculations/'+id);
        }

        function getRisk(id){
            return $http.get('http://localhost:9000/api/risks/project/'+ id);
        }

        function getMatrix(id){
            return $http.get('http://localhost:9000/api/risks/matrix/'+ id);
        }

        function finishTask(id,realHour){
            return $http.get('http://localhost:9000/api/tasks/finished/'+id+'/'+realHour);
        }

        function upload(list){
            return $http.post('http://localhost:9000/api/tasks/bulk',list)
        }

        function setCurrentRisk(risk){
            currentRisk = risk;
        }

        function getCurrentRisk(){
            return currentRisk;
        }
    }
})();
