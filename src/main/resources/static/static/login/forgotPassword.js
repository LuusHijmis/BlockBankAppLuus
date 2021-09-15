// @author hannahvd

document.addEventListener('DOMContentLoaded', forgot)

function forgot() {
    document.getElementById('submit').addEventListener('click', () => {

        const email = document.getElementById(`email`).value.toString() //DIT IS GOED vind ik

        fetch('http://localhost:8080/forgot',
            {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: email
            })
            .then(handleErrors)
            .then(response => response.text())
            .then(data => {
                console.log(data);
                window.alert("E-mail sent");
            })
    })

    function handleErrors(response) {
        if (!response.ok) {
            document.getElementById('forgot').reset();
            window.alert("There was an error :(");
            throw Error(response.statusText);
        }
        return response;
    }
}


//bla???????
// getToken();
//
// function getToken(){
//     const stringUrl = URL.split(`=`)
//     const token = stringUrl[1];
//     console.log(token);
//     if (token === undefined) {
//         document.getElementById('submitNewPasswordForm').style.display="none"
//     } else {
//         localStorage.setItem('Authorization', token);
//         document.getElementById('sumbitEmailForm').style.display="none"
//     }
// }