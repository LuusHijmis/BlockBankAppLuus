/*@author Karish Resodikromo
* Javascript voor logout functie
* */
    document.getElementById("#logout").addEventListener("click", function () {
        alert("log out succesfull");
        localStorage.removeItem('Authentication');
        window.location.replace("http://localhost:8080/static/login/login.html");
    })












