// app.js

// API Base URL
const BASE_URL = "http://80.91.91.91:8080/api";

// Sign-Up Functionality
function signup() {
    const firstname = document.getElementById('firstname').value;
    const lastname = document.getElementById('lastname').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch(`${BASE_URL}/signup`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ firstname, lastname, email, password }),
    })
        .then(response => {
            if (response.ok) {
                alert("Congrats! Account created successfully.");
                window.location.href = 'signin.html';
            } else {
                alert("Error creating account. Please try again.");
            }
        })
        .catch(error => console.error('Error:', error));
}

// Sign-In Functionality
function signin() {
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch(`${BASE_URL}/signin`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password }),
    })
        .then(response => {
            if (response.ok) {
                response.json().then(data => {
                    alert("Login Success!");
                    localStorage.setItem('firstname', data.firstname);
                    window.location.href = 'home.html';
                });
            } else {
                alert("Invalid email or password.");
            }
        })
        .catch(error => console.error('Error:', error));
}

// On Home Page Load
function loadHomePage() {
    const firstname = localStorage.getItem('firstname');
    if (!firstname) {
        alert("You are not logged in!");
        window.location.href = 'signin.html';
    } else {
        document.getElementById('welcome-message').innerText = `Hi ${firstname}`;
    }
}

// Logout Functionality
function logout() {
    localStorage.clear();
    window.location.href = 'signin.html';
}

