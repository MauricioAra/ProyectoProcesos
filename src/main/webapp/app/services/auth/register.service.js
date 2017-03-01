(function () {
    'use strict';

    angular
        .module('proyectoProcesosApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();
