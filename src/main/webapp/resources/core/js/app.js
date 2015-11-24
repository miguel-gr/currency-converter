'use strict';

/**
 * @ngdoc overview
 * @name uizpApp
 * @description
 * # uizpApp
 *
 * Main module of the application.
 */
angular
  .module('uizpApp', [
    //'ngCookies',
    //'ngResource',
    'ngRoute'
    //'ngSanitize'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'internalViews/main',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/about', {
        templateUrl: 'internalViews/about',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
