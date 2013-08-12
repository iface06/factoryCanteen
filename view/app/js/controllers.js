'use strict';

/* Controllers */

function CurrentMenuCtrl($scope, $http) {
    $scope.weekdays = new Array();
    $scope.menu = {};
    var today = Date.today();
    
    console.log('/menus/'+ Date.today().getTime());
    $http.get('/menus/'+ Date.today().getTime()).success(function(data) {
        $scope.menu = data;
        jQuery.each($scope.menu.weekdays, function(index, date) {
            $scope.weekdays[index] = Date.parse(date).toString("dddd");
        });
    }); 

}
