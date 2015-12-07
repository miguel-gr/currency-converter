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
    /*$routeProvider
      .when('/', {
        templateUrl: 'currencyRates',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/userInfo', {
        templateUrl: 'userViews/userInfo',
        controller: 'AboutCtrl',
        controllerAs: 'aboutCtrl'
      })
      .when('/about', {
        templateUrl: 'userViews/about',
        controller: 'AboutCtrl',
        controllerAs: 'aboutCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });*/
  });
