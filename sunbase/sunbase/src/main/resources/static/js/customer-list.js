// Search customers based on name
function searchCustomers() {
    var searchInput = $('#searchInput').val();

    // Make Ajax request to search for customers
    $.ajax({
        url: '/api/customers',
        dataType: 'json',
        type: 'GET',
        headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('jwtToken')
                },
        data: { search: searchInput },
        success: function (data) {
            // Handle the response, e.g., update the customer table with search results
            console.log(data);

            // Clear existing table content
            $('#customerTable').empty();

            // Update the DOM with search results
            if (data.length > 0) {
                // Create table header
                var tableHeader = '<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th></tr>';
                $('#customerTable').append(tableHeader);

                // Populate table rows with search results
                data.forEach(function (customer) {
                    var tableRow = '<tr><td>' + customer.id + '</td><td>' + customer.first_name + '</td><td>' + customer.last_name + '</td><td>' + customer.email + '</td></tr>';
                    $('#customerTable').append(tableRow);
                });
            } else {
                // Display a message if no results found
                $('#customerTable').append('<tr><td colspan="4">No results found</td></tr>');
            }
        },
        error: function (error) {
            // Handle errors
            console.error('Error:', error);
            toastr.error(error.responseText);
        },
        complete: function (xhr, status, err) {
            // Any post-request processing
        }
    });
}

$(document).ready(function () {
    fetchCustomers();
});
