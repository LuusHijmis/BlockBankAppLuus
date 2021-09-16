
function logout() {
    console.log("logging out")
    const logout = document.querySelector('#logout')
    logout.addEventListener("click", function () {
        alert("log out succesfull")
        window.localStorage.clear();
    })
}

// fetch('http://localhost:8080/logout',
//     {
//         method: 'POST',
//         headers: {'Content-Type': 'application/json'},
//         body: JSON.stringify
//     })
//     .then(data => {
//         localStorage.removeItem('Authentication', data);
//         window.location.pathname = '/static/login/login.html';
//     })
// }








