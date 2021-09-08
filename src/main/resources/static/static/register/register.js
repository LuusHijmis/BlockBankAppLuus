document.addEventListener('DOMContentLoaded', main)

function main() {
    document.getElementById('submit').addEventListener('click', ()=>{
        //check formulier voor fouten
        if(fullFormValidation()) {
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
                    alert('request successful');
                    window.location.replace("http://localhost:8080/static/login/login.html");
                },
                fail: function(xhr, status, errorThrown){
                    console.log(xhr);
                    console.log(status);
                    console.log(errorThrown);
                    alert('request failed');
                }
            });
        }
    })
}

function fullFormValidation() {
    var validForm = false;
    if(checkFormNotEmpty()) {
        // check de velden.
        var emailFormat = checkEmailFormat(document.getElementById(`email_f`));
        var fieldIsAlphanumeric = checkIfFieldIsAlphanumeric(document.getElementById(`postalcode_f`))
        var fieldIsAlphabetic = checkIfFieldIsAlphabetic();
        var checkIfBsnIsValid = checkIfBsnIsValid(document.getElementById("bsn_f"))

        if (emailFormat && fieldIsAlphabetic && fieldIsAlphanumeric && checkIfBsnIsValid) {
            validForm = true;
        }
    }

    return validForm;
}

function checkFormNotEmpty() {
    var fields = ["Username", "Password", "Email adress", "First name", "Last name", "BSN", "Date of birth", "Street Name",
        "House Number" , "Postal code", "City", "Country"]
    var fieldname;
    var i, l = fields.length;
    for ( i = 0; i < l; i++) {
        fieldname = fields[i];
        if (document.forms["register"][fieldname].value === "") {
            alert(fieldname + " must be filled in.");
            document.forms["register"][fieldname].focus();
            return false;
        }
    }
    return true;
}

function checkIfFieldIsAlphabetic() {
    var fields = ["First name", "Prefix", "Last name", "Street Name", "City", "Country"]
    var fieldname;
    var regex = /^[a-zA-z]*$/;

    var i, l = fields.length;
    for ( i = 0; i < l; i++) {
        fieldname = fields[i];
        if (!document.forms["register"][fieldname].value.match(regex)) {
            alert(fieldname + " must only contain alphabetic characters.");
            document.forms["register"][fieldname].focus();
            return false;
        }
    }
    return true;
}

function checkIfFieldIsAlphanumeric(field) {
    var regex = /^[0-9a-zA-Z]+$/;
    if(field.value.match(regex)) {
        return true;
    } else {
        alert(field.getAttribute('name') + " is invalid." );
        field.focus();
        return false;
    }

}

function checkIfBsnIsValid(bsn) {
    if(bsn.value.length === 9) {
        return true;
    } else {
        alert(bsn.getAttribute('name') + " ." );
        bsn.focus();
        return false;
    }
}

function checkEmailFormat(email) {
    var regex = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+(?:[A-Z]{2}|com|org|net|gov|biz|info|mobi|name|nl|ru|ch)\b$/;
    if(email.value.match(regex)) {
        return true;
    } else {
        alert("Please enter a valid e-mail adress.");
        email.focus();
        return false;
    }
}

