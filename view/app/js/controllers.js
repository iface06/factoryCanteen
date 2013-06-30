'use strict';

/* Controllers */

function CurrentMenuCtrl($scope, $http){
    $scope.menu = {'date' : '12/2013', 
                   'days' : [{'weekDay' : 'Monday', 
                              'first' : 'Surf & Turf', 
                              'second' : 'Gemüse Toskana', 
                              'third' : 'Schnipo'
                             },
                         {'weekDay' : 'Thuesday', 
                              'first' : 'Surf & Turf', 
                              'second' : 'Gemüse Toskana', 
                              'third' : 'Schnipo'
                             }]
                };
    $scope.canteens = [{'name' : 'Essen'}, {'name' : 'Mühlheim a. d. Ruhr'}];
    $scope.selectCanteen = function(){
        window.alert("Yep...");
    };
    
    $http.get('').success(function(data) {
        $scope.menu = data;
    });
    
}
