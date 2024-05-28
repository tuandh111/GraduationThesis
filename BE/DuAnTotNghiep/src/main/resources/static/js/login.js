let app = angular.module("BaiThi", ["ngRoute"]);
app.controller('SignInController', ['$scope', '$http', function ($scope, $http) {
    $scope.signIn = function () {
        console.log('Login');
        $scope.checkInputFields();
        if (!$scope.email || !$scope.password) {
            return;
        }
        console.log($scope.email + $scope.password);
        var requestData = {
            email: $scope.email,
            password: $scope.password
        };

        $http.post('http://localhost:8080/api/v1/auth/authenticate', requestData)
            .then(function (response) {
                try {
                    var dataResponse = response.data;
                    console.log(dataResponse)
                    if (dataResponse.access_token && dataResponse.refresh_token) {
                        localStorage.setItem('accessToken', dataResponse.access_token);
                        localStorage.setItem('refreshToken', dataResponse.refresh_token);
                        console.log("mới" + localStorage.getItem("accessToken"))
                        Swal.fire({
                            title: "Thành công!",
                            text: "Đăng nhập thành công!",
                            icon: "success"
                        });
                        console.log('Login successful');
                    } else {
                        Swal.fire({
                            title: "Lỗi!",
                            text: "Dữ liệu trả về từ server không hợp lệ!",
                            icon: "error"
                        });
                        console.error('Invalid data from server');
                    }
                } catch (error) {
                    Swal.fire({
                        title: "Lỗi!",
                        text: "Đã xảy ra lỗi khi xử lý dữ liệu từ server!",
                        icon: "error"
                    });
                    console.error('Error:', error);
                }
            })
            .catch(function (error) {
                Swal.fire({
                    title: "Lỗi!",
                    text: "Đăng nhập không thành công. Vui lòng thử lại!",
                    icon: "error"
                });
                console.error('Error:', error);
            });
    };
    $scope.checkInputFields = function () {
        if (!$scope.email) {
            $scope.emailError = true;
        } else {
            $scope.emailError = false;
        }
        if (!$scope.password) {
            $scope.passwordError = true;
        } else {
            $scope.passwordError = false;
        }
    };

}]);
app.controller('SignUpController', ['$scope', '$http', function ($scope, $http) {
    $scope.signUp = function () {
        console.log('Login');
        $scope.checkInputFields();
        if (!$scope.Firstname || !$scope.Lastname || !$scope.Email || !$scope.ConfirmPassword || !$scope.Password || $scope.ConfirmPassword !== $scope.Password) {
            return;
        }
        // Kiểm tra dữ liệu trước khi gửi yêu cầu
        console.log("Firstname: " + $scope.Firstname + " Lastname: " + $scope.Lastname + " Email: " + $scope.Email + " Password: " + $scope.Password + " Confirm password: " + $scope.ConfirmPassword);
        var requestData = {
            firstname: $scope.Firstname,
            lastname: $scope.Lastname,
            email: $scope.Email,
            password: $scope.Password
        };
        $http.post('http://localhost:8080/api/v1/auth/register', requestData)
            .then(function (response) {
                var dataResponse = response.data;
                if (dataResponse.message === 'ErrorEmail') {
                    $scope.emailExits = true
                    return
                } else {
                    $scope.emailExits = false
                }
                try {
                    console.log("mới" + localStorage.getItem("accessToken"))
                    Swal.fire({
                        title: "Thành công!",
                        text: "Đăng ký thành công!",
                        icon: "success"
                    });
                    $scope.clearForm();
                    console.log('Register successful');
                } catch (error) {
                    Swal.fire({
                        title: "Lỗi!",
                        text: "Đã xảy ra lỗi khi xử lý dữ liệu từ server!",
                        icon: "error"
                    });
                    console.error('Error:', error);
                }
            })
            .catch(function (error) {

                Swal.fire({
                    title: "Lỗi!",
                    text: "Đăng ký không thành công. Vui lòng thử lại!",
                    icon: "error"
                });
                console.error('Error1:', error);
            });
    };
    $scope.checkInputFields = function () {
        if (!$scope.Firstname) {
            $scope.firstnameError = true;
        } else {
            $scope.firstnameError = false;
        }
        if (!$scope.Lastname) {
            $scope.lastnameError = true;
        } else {
            $scope.lastnameError = false;
        }
        if (!$scope.Email) {
            $scope.emailError = true;
        } else {
            $scope.emailError = false;
        }
        if (!$scope.Password) {
            $scope.passwordError = true;
        } else {
            $scope.passwordError = false;
        }
        var passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9]).{6,}$/; // Regex kiểm tra mật khẩu ít nhất 6 ký tự và phải chứa ít nhất một chữ cái và một số
        if ($scope.Password && !passwordRegex.test($scope.Password)) {
            $scope.passwordFormatError = true;
        } else {
            $scope.passwordFormatError = false;
        }
        if (!$scope.ConfirmPassword) {
            $scope.confirmError = true;
        } else {
            $scope.confirmError = false;
        }
        if ($scope.ConfirmPassword !== $scope.Password) {
            $scope.confirmPasswordError = true;
        } else {
            $scope.confirmPasswordError = false;
        }
    };
    $scope.clearForm = function () {
        $scope.Firstname = '';
        $scope.Lastname = '';
        $scope.Email = '';
        $scope.Password = '';
        $scope.ConfirmPassword = '';
    }
}]);
