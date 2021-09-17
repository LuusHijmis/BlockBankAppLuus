//@author hannahvd

function hidePassword() {
    let pw1 = document.getElementById("pw1");
    let pw2 = document.getElementById("pw2")
    if (pw1.type === "password" || pw2.type === "password") {
        pw1.type = "text";
        pw2.type = "text";
    } else {
        pw1.type = "password";
        pw2.type = "password";
    }
}

//document.addEventListener('DOMContentLoaded', getToken) //nee
// getToken();
//
// function getToken() {
//     const stringUrl = window.location.pathname.split(`=`) //URL.split(`=`)
//     const token = stringUrl[1];
//     console.log(token);
//     if (token === undefined) {
//         window.alert("Link is invalid or token has expired")
//         //document.getElementById('reset').style.display="none"
//     } else {
//         localStorage.setItem('Authorization', token);
//     }
// }

//window.location.assign("new target URL"); //wil ik nie

document.addEventListener('DOMContentLoaded', resetPassword);

function resetPassword() {
    document.getElementById('submit').addEventListener('click', () => {

        getToken();

        function getToken() {
            const stringUrl = window.location.pathname.split(`=`) //URL.split(`=`)
            const token = stringUrl[1];
            console.log(token);
            if (token === undefined) {
                window.alert("Link is invalid or token has expired")
                //document.getElementById('reset').style.display="none"
            } else {
                localStorage.setItem('Authorization', token);
            }
        }


        //checkPasswordsMatch();
        let password = document.getElementById('pw1');
        let token = localStorage.getItem('Authorization');

        //send reset request
        fetch('http://localhost:8080/reset',
            {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: password + token
            })
            .then(handleErrors)
            .then(response => response.text())
            .then(msg => {
                window.alert(msg)
                window.location.pathname = 'static/login/login.html'
                localStorage.removeItem('Authorization');
            })
    })
}

function checkPasswordsMatch() {
    let pw1 = document.getElementById('pw1');
    let pw2 = document.getElementById('pw2');
    if (pw1.valueOf() !== pw2.valueOf()) { //.value()
        document.getElementById('reset').reset();
        window.alert("Passwords do not match"); //window.alert("bla");
    }
}

function handleErrors(response) {
    if (!response.ok) {
        document.getElementById('pw1').reset();
        document.getElementById('pw2').reset();
        if (response.status === 400) {
            window.alert("No account found :(");
        }
        throw Error(response.statusText);
    }
    return response;
}