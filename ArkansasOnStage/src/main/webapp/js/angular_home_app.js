
var stageApp = angular.module("ArkansasOnStageApp",[]);
stageApp.controller('StageCtrl', function($scope, $http) {	

	$scope.getEverything = function() {
		$scope.resource = '/ArkansasOnStage/webapi/stage/everything';
		$http.get($scope.resource).then(function(response) {
		alert("Programs Gathered");
		});
	}
	
	$scope.emptyEverything = function() {
		alert("Strike the Stage");
		$scope.resource = '/ArkansasOnStage/webapi/stage/empty';
		$http.get($scope.resource).then(function(response) {
		});
	}
	
});
	

