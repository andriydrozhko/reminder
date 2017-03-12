var app = angular.module('eventsapp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/events/list.html',
        controller: 'ListCtrl'
    }).when('/create', {
        templateUrl: 'views/events/create.html',
        controller: 'CreateCtrl'
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('ListCtrl', function ($scope, $http) {
    $http.get('/api/v1/events').success(function (data) {
        $scope.events = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    });

    //$scope.todoStatusChanged = function (event) {
    //    console.log(todo);
    //    $http.put('/api/v1/events/' + event.id, event).success(function (data) {
    //        console.log('status changed');
    //    }).error(function (data, status) {
    //        console.log('Error ' + data)
    //    })
    //}
});

app.controller('CreateCtrl', function ($scope, $http, $location) {

    console.log($("#datetimepicker12").data('date'))
    console.log($scope.vm)

    $scope.createEvent = function () {
        if(undefined == $scope.event) {
            $scope.event = {};
        }
        $scope.event.eventDate = $("#datetimepicker12").data('date');
        console.log($scope.event);
        $http.post('/api/v1/events', $scope.event).success(function (data) {
           $location.path('/');
        }).error(function (data, status) {
           console.log('Error ' + data)
        })
    }
});
