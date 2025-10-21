


document.getElementById('login-form').addEventListener('submit', function(event) {

    let username = document.getElementById('username');
    let password = document.getElementById('password');

    let isValid = true;

    if(username.value.trim() === ''){

       alert('username cannot be empty');
       isValid = false;
    }

    if(password.value.trim() === ''){

       alert('password cannot be empty');
       isValid = false;
    }

    if(!isValid){
        event.preventDefault();
    }


});




