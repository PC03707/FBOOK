app.controller("statis-ctrl", function($scope, $http) {

	$scope.initialize = function() {
		$http.get("/rest/products/demslsp").then(resp => {
			$scope.slsp = resp.data;
		});
		$http.get("/rest/products/demslkh").then(resp => {
			$scope.slkh = resp.data;
		});
		$http.get("/rest/products/demsldh").then(resp => {
			$scope.sldh = resp.data;
		});
		$http.get("/rest/products/demslcd").then(resp => {
			$scope.slcd = resp.data;
		});
		$http.get("/rest/products/ddhanghna").then(resp => {
			$scope.ddhanghna = resp.data;
		});
		$http.get("/rest/products/tongtienhomnay").then(resp => {
			$scope.tongtienhomnay = resp.data;
		});

	}
	$scope.initialize();
});