


// Attach the validation function to the form's submit event
document.getElementById('signup-form').addEventListener('submit', function(event) {
    if (!validateRegistrationForm()) {
        event.preventDefault(); // Prevent form submission if validation fails
    }
});



function validateRegistrationForm() {
    const username = document.getElementById('username');
    const email = document.getElementById('email');
    const password = document.getElementById('password');
    const confirmPassword = document.getElementById('confirmPassword');

    let isValid = true;

    // Validate Username
    if (username.value.trim() === '') {
        alert('Username cannot be empty.');
        isValid = false;
    } else if (username.value.length < 3 || username.value.length > 25) {
        alert('Username must be between 3 and 25 characters.');
        isValid = false;
    }

    // Validate Email
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (email.value.trim() === '') {
        alert('Email cannot be empty.');
        isValid = false;
    } else if (!emailRegex.test(email.value)) {
        alert('Please enter a valid email address.');
        isValid = false;
    }

    // Validate Password
    if (password.value.length < 8) {
        alert('Password must be at least 8 characters long.');
        isValid = false;
    }

    // Validate Confirm Password
    if (password.value !== confirmPassword.value) {
        alert('Passwords do not match.');
        isValid = false;
    }

    return isValid; // Return true if all validations pass, false otherwise
}

