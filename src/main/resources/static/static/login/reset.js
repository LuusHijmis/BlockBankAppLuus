// @author hannahvd

document.addEventListener('DOMContentLoaded', reset)

function reset() {
    document.getElementById('submit').addEventListener('click', () => {

        //TODO: dit kan anders
        const resetData = {
            emailaddress: document.getElementById(`email`).value
        }

        fetch('http://localhost:8080/reset',
            {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(resetData)
            })
            .then(handleErrors)
            .then(response => response.text())
            .then(data => {
                localStorage.setItem('Authentication', data); //TODO: if email excists > give "reset mail succesfull sent" message //if not > give "emailaddress not found" message
            })
    })

    function handleErrors(response) {
        if (!response.ok) {
            document.getElementById('form').reset();
            if (response.status === 401) {
                window.alert("Incorrect credentials");
            } else {
                window.alert("Your account is blocked. Contact admin@blockbank.com"); //oid
            }
            throw Error(response.statusText);
        }
        return response;
    }
}
