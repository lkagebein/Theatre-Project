
var stageApp = angular.module("StageApp",[]);
stageApp.controller('StageCtrl', function($scope, $http) {	
	$scope.hideWelcomeInformation = false;
	$scope.hideVenueInformation = true;
	$scope.hideVenueSearchResults = true;
	$scope.hideShowInformation = true;
	$scope.hideShowSearchResults = true;
	$scope.hidePerformanceInformation = true;
	$scope.hidePerformanceSearchResults = true;
	
	//Welcome
	$scope.welcomeInformation = function() {
		$scope.hideWelcomeInformation = false;
		$scope.hideVenueInformation = true;
		$scope.hideVenueSearchResults = true;
		$scope.hideShowInformation = true;
		$scope.hideShowSearchResults = true;
		$scope.hidePerformanceInformation = true;
		$scope.hidePerformanceSearchResults = true;
	};
	
	//Venue
	$scope.venueInformation = function() {
		$scope.hideWelcomeInformation = true;
		$scope.hideVenueInformation = false;
		$scope.hideVenueSearchResults = true;
		$scope.hideShowInformation = true;
		$scope.hideShowSearchResults = true;
		$scope.hidePerformanceInformation = true;
		$scope.hidePerformanceSearchResults = true;
	};

	$scope.getVenues = function() {
		$scope.hideVenueSearchResults = false;
		$scope.resource = '/ArkansasOnStage/webapi/stage/venues';
		$http.get($scope.resource).then(function(response) {
			$scope.chosenVenue = response.data;
		});
	}
	
	//Shows
	$scope.showInformation = function() {
		$scope.hideWelcomeInformation = true;
		$scope.hideVenueInformation = true;
		$scope.hideVenueSearchResults = true;
		$scope.hideShowInformation = false;
		$scope.hideShowSearchResults = true;
		$scope.hidePerformanceInformation = true;
		$scope.hidePerformanceSearchResults = true;
	};

	$scope.getShows = function() {
		$scope.hideShowSearchResults = false;
		$scope.resource = '/ArkansasOnStage/webapi/stage/shows';
		$http.get($scope.resource).then(function(response) {
			$scope.chosenShow = response.data;
		});
	}

	//Performances
	
	$scope.convertDateToString = function(searchDate) {    
        //convert Date to String
        var year = searchDate.getFullYear();
        var mo = searchDate.getMonth() + 1; //0 based
        var day = searchDate.getDate();
        
        var moString = "" + mo;
        if (mo < 10) {
            moString = "0" + mo;
        }
        var dayString = "" + day;
        if (day < 10) {
            dayString = "0" + day;
        }
        
        var dateString = "" + year + "-" + moString + "-" + dayString;
        
        return dateString;
    }
	

	
	$scope.performanceInformation = function() {
		$scope.hideWelcomeInformation = true;
		$scope.hideVenueInformation = true;
		$scope.hideVenueSearchResults = true;
		$scope.hideShowInformation = true;
		$scope.hideShowSearchResults = true;
		$scope.hidePerformanceInformation = false;
		$scope.hidePerformanceSearchResults = true;
	};

	$scope.getPerformances = function() {
		$scope.resource = '/ArkansasOnStage/webapi/stage/performances';
		$http.get($scope.resource).then(function(response) {
			$scope.hidePerformanceSearchResults = false;
			$scope.chosenPerformance = response.data;
		});
	}
	
	$scope.getPerformancesBySingleDate = function(sDate) {
		var dateString = $scope.convertDateToString(sDate);
		$scope.resource = '/ArkansasOnStage/webapi/stage/performances/' + dateString;
		$http.get($scope.resource).then(function(response) {
			$scope.hidePerformanceSearchResults = false;
			$scope.chosenPerformance = response.data;
		});
	}
	
	$scope.getPerformancesByDateRange = function(sDate1, sDate2) {
		var dateString1 = $scope.convertDateToString(sDate1);
		var dateString2 = $scope.convertDateToString(sDate2);
		$scope.resource = '/ArkansasOnStage/webapi/stage/performances/' + dateString1 + '/' + dateString2;
		$http.get($scope.resource).then(function(response) {
			$scope.hidePerformanceSearchResults = false;
			$scope.chosenPerformance = response.data;
		});
	}

	
});
