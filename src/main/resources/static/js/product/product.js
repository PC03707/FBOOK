let host = "http://localhost:8080/rest";
const app = angular.module("app", []);
app.controller("ctrl", function($scope, $http) {
	$scope.cartdetails = []
	$scope.products = []
	$scope.username = []
	$scope.cartid = 0
	$scope.selected = []
	$scope.total = 0

	
	$scope.items = [];
	$scope.status = [];
	$scope.form = {};


	//start load products

	$scope.product = 
		function() {
			var url = `${host}/products`;
			$http.get(url).then(resp => {
				$scope.products = resp.data;			
		});
		}	
	$scope.product();

	// end load products

	//	-------------------------------------------------------------------------------------------------------------------

	// start handle cart

	// load cart data
	$scope.getcartid = function() {
		$scope.username = $("#username").text();
		var url = `${host}/cart/` + $scope.username;
		$http.get(url).then(resp => {
			$scope.cart = resp.data;
			$scope.cartid = resp.data.id;
			//console.log("Success", $scope.cartid)
		}).catch(error => {
			console.log("Error", error)
		})
	}
	// lấy toàn bộ dữ liệu từ giỏ hàng chi tiết
	$scope.getcartdetails = function() {
		$scope.username = $("#username").text();
		var url = `${host}/cart/cartdetails/` + $scope.username;//+ $scope.cart;
		$http.get(url).then(resp => {
			$scope.cartdetails = resp.data;
			console.log("Success", resp)
		}).catch(error => {
			console.log("Error", error)
		})
	}

	// thêm sản phẩm vào giỏ hàng
	$scope.addcart = function(p) {
		var url = `${host}/cart/addcart`;
		$scope.data = {
			price: p.price,
			quantity: 1,
			book: { id: p.id },
			cart: { id: $scope.cartid }
		}
		$http.post(url, $scope.data).then(resp => {
			console.log("Success", resp);
			Swal.fire("Success", "Add to cart successfully!", "success");
			$scope.getcartdetails();
			$scope.getTotalItem()
		}).catch(error => {
			Swal.fire("Error", "Add to cart failure!", "error");
			console.log(error)
		})
	}

	// xóa sản phẩm khỏi giỏ hàng
	$scope.deleteid = function(id) {
		var url = `${host}/cart/delete/${id}`;
		$http.delete(url).then(resp => {
			Swal.fire("Success", "Đã xóa sản phẩm khỏi giỏ hàng!", "success");
			console.log("Success", resp);
			$scope.getcartdetails();
		}).catch(error => {
			Swal.fire("Error", "Xóa sản phẩm thất bại!", "error");
			console.log("Error", error);
		})
	}

	// Xóa tất cả sản phẩm 
	//		chưa làm đc
	//		$scope.deleteall = function() {
	//			var url = `${host}/cart/deleteall/`+$scope.cartid;
	//			console.log(url)
	//			$http.delete(url).then(resp => {
	//				console.log("Success", resp);
	//				$scope.getcartdetails();
	//			}).catch(error => {
	//				console.log("Error", error);
	//			})
	//		}

	// Giảm số lượng sản phẩm
	$scope.updateminusqty = function(cd) {
		$scope.selected = $scope.selected.filter(item => item !== cd);
		if (isNaN(cd.quantity)) {
			Swal.fire("Error", "Invalid quantity!", "error");
			cd.quantity = 1;
		}
		if (cd.quantity <= 1) {
			$scope.deleteid(cd.id)
			$scope.total = $scope.sumtotal($scope.selected)
		} else {
			var url = `${host}/cart/updateqty`;
			cd.quantity--;
			$http.put(url, cd).then(resp => {
				//console.log(resp.data)
				$scope.selected.push(cd);
				$scope.total = $scope.sumtotal($scope.selected)
				console.log("Success", resp)
				//Swal.fire("Success", "Đã giảm số lượng sản phẩm!", "success");
			}).catch(error => {
				//Swal.fire("Error", "thất bại!", "error");
				console.log("Error", error);
			})
		}
	}

	// Tăng số lượng sản phẩm
	$scope.updateplusqty = function(cd) {
		$scope.selected = $scope.selected.filter(item => item !== cd);
		if (isNaN(cd.quantity) || cd.quantity < 0) {
			Swal.fire("Error", "Invalid quantity!", "error");
			cd.quantity = 1;
		}
		var url = `${host}/cart/updateqty`;
		cd.quantity++;
		$http.put(url, cd).then(resp => {
			$scope.selected.push(cd);
			$scope.total = $scope.sumtotal($scope.selected)
			console.log("Success", resp)
			//				Swal.fire("Success", "Đã tăng số lượng sản phẩm!", "success");
		}).catch(error => {
			Swal.fire("Error", "thất bại!", "error");
			console.log("Error", error);
		})
	}

	// thay đổi số lượng trên cart
	$scope.updateqty = function(cd) {
		//				$scope.selected = $scope.selected.filter(item => item !== cd);
		if (isNaN(cd.quantity) || cd.quantity < 0) {
			Swal.fire("Error", "Invalid quantity!", "error");
			cd.quantity = 1;
		}
		var url = `${host}/cart/updateqty`;
		$http.put(url, cd).then(resp => {
			//				$scope.selected.push(resp.data);
			//				$scope.total = $scope.sumtotal($scope.selected)
			console.log(resp)
		}).catch(error => {
			Swal.fire("Error", "thất bại!", "error");
			console.log("Error", error);
		})
	}

	//tổng item
	$scope.getTotalItem = function() {
		var totalQuantity = $scope.cartdetails.length;
		return totalQuantity;
	}

	// ------------------------------------------------

	$scope.exist = function(cd) {
		return $scope.selected.indexOf(cd) > -1;
	}

	$scope.toggleSelection = function(cd) {
		$scope.total = 0;
		var idx = $scope.selected.indexOf(cd);
		if (idx > -1) {
			$scope.selected.splice(idx, 1);
			$scope.total = $scope.sumtotal($scope.selected);
		} else {
			$scope.selected.push(cd)
			$scope.total = $scope.sumtotal($scope.selected);
		}
	}

	// tính tổng tiền 
	$scope.sumtotal = function(select) {
		var total = 0;
		for (var i = 0; i < select.length; i++) {
			total += select[i].price * select[i].quantity;
		}
		if (select.length < 1) {
			total = 0;
			return total;
		}
		return total;
	}

	// thanh toán
	$scope.order = {
		//			createDate: new Date(),
		//			address: "",
		//			billtotal: $("#total").val(),
		//			account:{username: $("#username").val()},
		//			ship: 15000,
		//			voucher: {voucherid: $("#vouchercode").val()},
		//			status: {id: 1},
		//			get billDetails() {
		//				return $scope.cart.items.map(item => {
		//					return {
		//						book: { id: item.id },
		//						price: item.price,
		//						quantity: item.qty
		//					}
		//				});
		//			},
		purchase() {
			alert("hshshs")
			//				var order = angular.copy(this);
			//				// thuc hien dat hang
			//				$http.post("/rest/orders", order).then(resp => {
			//					alert("dat hang thanh cong");
			//					$scope.cart.clear();
			//					location.href = "order/detail/" + resp.data.billid;
			//				}).catch(error => {
			//					alert("dat hang that bai");
			//					console.log(error)
			//				})
		}
	}
	$scope.pager = {
		page: 0,
		size: 8,
		get products() {
			var start = this.page * this.size;
			return $scope.products.slice(start, start + this.size);

		},
		get count() {
			return Math.ceil(1.0 * $scope.products.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}


	}
	// oder 
	$scope.initialize = function() {
		$scope.username = $("#username").text();
		var url = `${host}/order/` + $scope.username;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.date = new Date(item.createDate)
			})
		});
			$http.get("/rest/status").then(resp => {
			$scope.status = resp.data;
		});
		$http.get("/rest/order/index").then(resp => {
			$scope.list = resp.data;
			console.log(resp.data);
		});		
	}
	
	 $scope.changeStatus = function (orderId, newStatusId) {
        $http.put("/rest/order/" + orderId + "/status?newStatusId=" + newStatusId)
            .then(function (response) {
                $scope.items.push(response.data); // Sửa lại thành response.data
                // Update interface after successful status change
                for (var i = 0; i < $scope.items.length; i++) {
                    if ($scope.items[i].id === orderId) {
                        $scope.items[i].status.id = newStatusId;
                        break;
                    }
                }
            }).catch(function (error) {
               	$scope.initialize();
                Swal.fire({
                    icon: 'success',
                    title: 'Success',
                    text: 'Order status updated successfully',
                    confirmButtonText: 'OK'
                });
            });
    };
    
    
    
    $scope.showOrderDetail = function (orderId) {
        $http.get("/rest/orderDetails/" + orderId)
            .then(function (response) {
                $scope.selectedOrderDetails = response.data;
                $('#orderDetailModal').modal('show'); // Hiển thị modal chứa danh sách sản phẩm
            })
            .catch(function (error) {
                console.error("Error fetching order details:", error);
            });
    };
     $scope.closeModal = function() {
        $("#orderDetailModal").modal("hide");
    };


	$scope.initialize();
	$scope.getcartid()
	$scope.getcartdetails();
	// end handle cart
})