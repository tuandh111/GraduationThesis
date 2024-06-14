console.log("home page loaded")
let app = angular.module("shoesHub", ["ngRoute"]);
let BASE_URL = "http://localhost:8080/";
app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: 'templates/shop/homepage.html',
        })
        .when("/home", {
            templateUrl: 'templates/shop/homepage.html',
        })
        .when("/index", {
            templateUrl: 'templates/shop/homepage.html',
        })
        .when("/shop/login", {
            templateUrl: 'templates/shop/login.html',
        })
        .when("/shop/register", {
            templateUrl: 'templates/shop/register.html',
        })
        .when("/shop/contact", {
            templateUrl: 'templates/shop/contact.html',
        })
        .when("/shop/cart", {
            templateUrl: 'templates/shop/cart.html',
        })
        .when("/shop/blog", {
            templateUrl: "templates/shop/blog.html",
        })
        .when("/shop/category", {
            templateUrl: "templates/shop/category.html",
        })
        .when("/shop/checkout", {
            templateUrl: "templates/shop/checkout.html",
        })
        .when("/shop/confirmation", {
            templateUrl: "templates/shop/confirmation.html",
        })
        .when("/shop/single-blog", {
            templateUrl: "templates/shop/single-blog.html",
        })
        .when("/shop/single-product/:id", {
            templateUrl: "templates/shop/single-product.html",
        })
        .when("/shop/single-product/:id", {
            templateUrl: "templates/shop/single-product.html",
        })
        .when("/shop/tracking-order", {
            templateUrl: "templates/shop/tracking-order.html",
        })
        .when("/admin", {
            templateUrl: 'templates/admin/dashboard.html',
        })
        .when("/admin/login", {
            templateUrl: 'templates/admin/login.html',
        })
        .when("/admin/register", {
            templateUrl: 'templates/admin/register.html',
        })
        .when("/admin/forgot-password", {
            templateUrl: 'templates/admin/forgot-password.html',
        })
        .when("/admin/tables", {
            templateUrl: 'templates/admin/tables.html',
        })
        .when("/admin/charts", {
            templateUrl: "templates/admin/charts.html",
        })
        .when("/admin/cards", {
            templateUrl: "templates/admin/cards.html",
        })
        .when("/admin/buttons", {
            templateUrl: "templates/admin/buttons.html",
        })
        .when("/admin/blank", {
            templateUrl: "templates/admin/blank.html",
        })
        .when("/admin/404", {
            templateUrl: "templates/admin/404.html",
        })
        .when("/admin/brands", {
            templateUrl: "templates/admin/brands.html",
        })
        .when("/admin/products", {
            templateUrl: "templates/admin/products.html",
        })
        .when("/admin/orders", {
            templateUrl: "templates/admin/orders.html",
        })
        .when("/admin/posts", {
            templateUrl: "templates/admin/posts.html",
        })
        .otherwise({
            redirectTo: "templates/shop/homepage.html"
        });
});

