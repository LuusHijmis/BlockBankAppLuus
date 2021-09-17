// @author hannahvd

document.addEventListener('DOMContentLoaded', forgot)

function forgot() {
    document.getElementById('submit').addEventListener('click', () => {

        const email = document.getElementById(`email`).value.toString()

        fetch('http://localhost:8080/forgot',
            {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: email
            })
            .then(handleErrors)
            .then(response => response.text())
            .then(data => {
                //console.log(data); //kan weg?
                window.alert("E-mail sent");
            })
    })
    //TODO: response uit body halen
    function handleErrors(response) {
        if (!response.ok) {
            document.getElementById('forgot').reset();
            if (response.status === 404) {
                window.alert("No account found :(");
            }
            throw Error(response.statusText);
        }
        return response;
    }
}