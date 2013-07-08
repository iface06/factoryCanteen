'use strict';

/* Controllers */

function CurrentMenuCtrl($scope, $http) {
    $scope.weekdays = new Array();
    $scope.menu = {};
    $http.get('/menus/28/2013').success(function(data) {
        $scope.menu = data;
        jQuery.each($scope.menu.weekdays, function(index, date) {
            $scope.weekdays[index] = Date.parse(date).toString("dddd");
        });
    });

}
