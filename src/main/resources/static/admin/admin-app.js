app = angular.module("admin-app",["ngRoute"]);

app.config(function($routeProvider){
	$routeProvider
	.when("/product",{
		templateUrl:"/admin/product/index.html",
		controller:"product-ctrl"	
	})
	.when("/user",{
		templateUrl:"/admin/user/index.html",
		controller:"account-ctrl"
	})
	.when("/order",{
		templateUrl:"/admin/order/index.html",
		controller:"order-ctrl"
	})
	.when("/author",{
		templateUrl:"/admin/author/index.html",
		controller:"author-ctrl"
	})	
	.when("/category",{
		templateUrl:"/admin/category/index.html",
		controller:"category-ctrl"
	})
	.otherwise({
		templateUrl:"/admin/Home.html",
		controller:"statis-ctrl"
	})
	
	
	
	
})