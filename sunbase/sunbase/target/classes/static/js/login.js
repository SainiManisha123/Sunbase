$('#loginForm').submit(function (event) {
debugger;
    event.preventDefault();

    // Get values from the form
    const username = $('#username').val();
    const password = $('#password').val();

    // Make Ajax request for login
    $.ajax({
        url: '/api/login',
        dataType: 'json',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            username: username,
            password: password
        }),
        success: function (data) {
            // Handle the response, e.g., redirect to customer list page if login successful
            localStorage.setItem('jwtToken', data.token);
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
