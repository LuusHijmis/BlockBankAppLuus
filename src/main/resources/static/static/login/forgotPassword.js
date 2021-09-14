// @author hannahvd

document.addEventListener('DOMContentLoaded', forgot)

function forgot() {
    document.getElementById('submit').addEventListener('click', () => {

        //TODO: dit kan anders
        const resetData = {
            email: document.getElementById(`email`).value
        }

        fetch('http://localhost:8080/forgot',
            {
                method: 'POST', //GET?
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(resetData)
            })
            .then(handleErrors)
            .then(response => response.text())
            .then(data => {
                localStorage.setItem('Authentication', data); //TODO: data correct afhandelen in .then
            })
    })

    //TODO: handleErrors aanpassen
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
