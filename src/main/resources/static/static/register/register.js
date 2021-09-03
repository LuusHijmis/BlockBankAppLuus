/*const form = document.getElementById("register");
const submit = document.getElementById("submit");
document.addEventListener('DOMContentLoaded', main)

function main() {
    submit.addEventListener('click', ()=>{
        //check formulier voor fouten

        //als er geen fouten zijn, maak een data bestand aan.
        let data = {
            "username": "testJava",
            "password": "HOOFDLETTERS.a1",
            "firstname": "bobbie",
            "prefix": ".",
            "lastname": "Kotik",
            "dateOfBirth": "1990-09-25",
            "bsn": 123,
            "emailAddress": "testJava",
            "address": "koningstraat",
            "houseNumber": 12,
            "affix": "assdwdsw",
            "postalCode": "1221JB",
            "city": "amsterdam",
            "country": "nederland"}; // javascript object

        // dan stuur dit request.
        fetch('http://localhost:8080/blockbank/registerDTO?' + data, {
            method: 'POST',
            body: JSON.stringify(data)  // moet worden omgezet naar een string
        })
            .then(response => {
                console.log(response)
                return response.json() }
            ).catch((error) => {console.error('Foutje', error);});
        //
    })
}*/

