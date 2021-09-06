document.addEventListener('DOMContentLoaded', main)

function main() {
    document.getElementById('submit').addEventListener('click', ()=>{
        //check formulier voor fouten

        //als er geen fouten zijn, maak een data bestand aan.
        const dataRaw = {
            username: document.getElementById(`username_f`).value,
            password: document.getElementById(`password_f`).value,
            firstname: document.getElementById(`firstname_f`).value,
            prefix: document.getElementById(`prefix_f`).value,
            lastname: document.getElementById(`lastname_f`).value,
            dateOfBirth: document.getElementById(`dateofbirth_f`).value,
            bsn: document.getElementById(`bsn_f`).value,
            emailAddress: document.getElementById(`email_f`).value,
            address: document.getElementById(`adress_f`).value,
            houseNumber: document.getElementById(`housenumber_f`).value,
            affix: document.getElementById(`affix_f`).value,
            postalCode: document.getElementById(`postalcode_f`).value,
            city: document.getElementById(`city_f`).value,
            country: document.getElementById(`country_f`).value};

        console.log(JSON.stringify(dataRaw));


        // dan stuur dit request.
        $.ajax({
            type : "POST",
            url : 'http://localhost:8080/registerDTO',
            data : JSON.stringify(dataRaw),
            contentType: "application/json",
            accepts: { json: "application/json"},
            success: function (data, status, xhr) {
                console.log(data);
                console.log(status);
                console.log(xhr);
            }
        });
    })
}

function validateFormNotEmpty() {
    var fields = ["Username", "Password", "Email adress", "First name", "Last name", "Date of birth", "Street Name",
        "House Number" , "Postal code", "City", "Country"]
    var fieldname;
    var i, l = fields.length;
    for ( i = 0; i < l; i++) {
        fieldname = fields[i];
        if (document.forms["register"][fieldname].value === "") {
            alert(fieldname + "must be filled in.");
            return false;
        }
    }
    return true;
}

