$('#addCustomerForm').submit(function (event) {
    event.preventDefault();

    // Get values from the form
    const firstName = $('#firstName').val();
    const lastName = $('#lastName').val();
    const street = $('#street').val();
    const address = $('#address').val();
    const city = $('#city').val();
    const state = $('#state').val();
    const email = $('#email').val();
    const phone = $('#phone').val();
    const password = $('#password').val(); // Added line for password
    // ... other fields

    // Make Ajax request to add a new customer
    $.ajax({
        url: '/api/customers',
        dataType: 'json',
        type: 'POST',
        contentType: 'application/json',
        headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('jwtToken')
                },
        data: JSON.stringify({
            first_name: firstName,
            last_name: lastName,
            street: street,
            address: address,
            city: city,
            state: state,
            email: email,
            phone: phone,
            password: password, // Include password in the data
            // ... other fields
        }),
        success: function (data) {
            // Handle the response, e.g., redirect to customer list page if addition successful
            console.log(data);
             window.location.href = '/customer-list.jsp';
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
});
