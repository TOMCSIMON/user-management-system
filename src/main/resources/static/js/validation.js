


// ATTACH THE VALIDATION FUNCTION TO THE FORM'S SUBMIT EVENT
document.getElementById('signup-form').addEventListener('submit', function(event) {
    if (!validateRegistrationForm()) {
        event.preventDefault(); // PREVENT FORM SUBMISSION IF VALIDATION FAILS
    }
});


function validateRegistrationForm() {

    const username = document.getElementById('userName');
    const email = document.getElementById('email');
    const password = document.getElementById('password');
    const confirmPassword = document.getElementById('confirmPassword');

    let isValid = true;

    // VALIDATE USERNAME
    if (username.value.trim() === '') {
        alert('Username cannot be empty.');
        isValid = false;
    } else if (username.value.length < 3 || username.value.length > 25) {
        alert('Username must be between 3 and 25 characters.');
        isValid = false;
    }

    // VALIDATE EMAIL
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (email.value.trim() === '') {
        alert('Email cannot be empty.');
        isValid = false;
    } else if (!emailRegex.test(email.value)) {
        alert('Please enter a valid email address.');
        isValid = false;
    }

    // VALIDATE PASSWORD
    if (password.value.length < 8) {
        alert('Password must be at least 8 characters long.');
        isValid = false;
    }

    // VALIDATE CONFIRM PASSWORD
    if (password.value !== confirmPassword.value) {
        alert('Passwords do not match.');
        isValid = false;
    }

    return isValid; // RETURN TRUE IF ALL VALIDATIONS PASS, FALSE OTHERWISE
}

