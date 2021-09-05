var app = angular.module("AnaliticaManager", []);
 
// Controller Part
app.controller("analiticaManagerController", function($scope, $http) {
	$scope.totalSumm="";
	$scope.day = new Date();
	$scope.card_number=0;
    $scope.products = [];
    $scope.productForm = {
		product_code:0,
        name: "",
		count: 0
        
	};
		
    $scope.submitSumm = function() {
        method = "get";
        
		url = 'http://localhost:8088/api/getTotalForDay/'+$scope.day.toISOString();
        
		console.log(url);
        $http({
            method: method,
            url: url
        }).then(
            function(res) { // success
			var formatter = new Intl.NumberFormat('ru');
				
                $scope.totalSumm = formatter.format(res.data);;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
    
	$scope.submitTopSale = function() {
        method = "get";
        
		url = 'http://localhost:8088/api/getBestForCard/'+$scope.card_number;
        console.log($scope.card_number);
        $http({
            method: method,
            url: url
        }).then(
            function(res) { // success
			$scope.products = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
     
    
});