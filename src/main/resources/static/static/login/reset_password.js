//@author hannahvd

const URL = window.location.href;
getToken();
function getToken() {
    const stringUrl = URL.split(`=`)
    const token = stringUrl[1];
    console.log(token);
    if (token === undefined) {
        window.alert("Link is invalid or token has expired\nYou will be redirected to our homepage")
        window.location.pathname = '/static/index/index.html'
    } else {
        localStorage.setItem('Authorization', token);
    }
}
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
function checkPasswordsMatch() {
    let pw1 = document.getElementById('pw1').value;
    let pw2 = document.getElementById('pw2').value;
    //let pw1 = String(document.querySelector('#pw1').value)
    if (pw1 !== pw2) { //.value()
        document.getElementById('reset').reset();
        window.alert("Passwords do not match"); //window.alert("bla");
    }
}

document.addEventListener('DOMContentLoaded', resetPassword);
function resetPassword() {
    document.getElementById('submit').addEventListener('click', () => {

        //checkPasswordsMatch();
        let password = document.getElementById('pw1').value;
        let token = localStorage.getItem('Authorization').valueOf();

        //send reset request
        fetch('http://localhost:8080/reset',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': token
                },
                body: password
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

function handleErrors(response) {
    if (!response.ok) {
        document.getElementById('pw1').reset();
        document.getElementById('pw2').reset();
        if (response.status === 400) {
            window.alert(response.statusText); //msg? //"No account has been found:("
        }
        throw Error(response.statusText);
    }
    return response;
}