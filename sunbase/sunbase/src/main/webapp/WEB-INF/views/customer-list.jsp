<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer List</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="shortcut icon" href="#">
</head>
<body>
    <div class="container">
        <h2>Customer List</h2>
        <input type="text" id="searchInput" placeholder="Search by name">
        <button onclick="searchCustomers()">Search</button>
        <table id="customerTable">
            <!-- Table content will be filled dynamically using JavaScript -->
        </table>
    </div>
    <script type="text/javascript" src="/js/customer-list.js"></script>
</body>
</html>
