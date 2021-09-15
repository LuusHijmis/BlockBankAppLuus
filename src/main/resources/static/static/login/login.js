//@author hannahvd

function hidePassword() {
    let password = document.getElementById("password");
    if (password.type === "password") {
        password.type = "text";
    } else {
        password.type = "password";
    }
}

document.addEventListener('DOMContentLoaded', login)

function login() {
    document.getElementById('submit').addEventListener('click', () => {

        //login.preventDefault();
          //preventDefault() to prevent refreshing the whole page every time the button is clicked,
          //because (...) it reloads the whole page once the event ('click' in this case) happens.

        const loginData = {
            username: document.getElementById(`username`).value,
            password: document.getElementById(`password`).value
        }

        fetch('http://localhost:8080/login',
            {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(loginData)
        })
            .then(handleErrors)
            .then(response => response.text())
            .then(data => {
                localStorage.setItem('Authentication', data); //sessionStorage?
                window.location.pathname = '/static/welcome/welcome.html'; //http://localhost:8080/static/welcome/welcome.html
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

















//GET token
// let sendReq = (ev) => {
//     let url = 'https://localhost:8080/blockbank/login'; //is dit de juiste url?
//     let request = new Request(url, {
//         method: 'GET',
//
//     });
//
//     fetch(request)
//         .then(handleErrors)
//         .then(response => response.headers)
//         .then(header => {
//             sessionStorage.setItem('Authentication', header[0]);
//             //hallo user bla
//             //shit uit header halen
//             //redirecten???
//         })
//         .catch(err => console.error(err.message));
//
// }













//let token = JSON.parse(sessionStorage.getItem('userJwt'));

//let h = new Headers();
//h.append('Authorization', `Bearer${token}`); //wat moet 'name' zijn? wat moet 'value' zijn?
//authentication type is which algorithm will be used for authentication. common type is "basic"
//"bearer tokens to access Oauth 2.0 protected resources"
//append() = not compatibility with browsers internet explorer + firefox for android

//-------------------------------------

// The btoa() method creates a Base64-encoded ASCII string from a binary string
//      (i.e., a String object in which each character in the string is treated as a byte of binary data).

// You can use this method to encode data which may otherwise cause communication problems, transmit it,
//     then use the atob() method to decode the data again.

//btoa() only encodes Strings of one (1) byte (dafuk)

// let jwtUser = 'bla';
// const encodedData = btoa(jwtUser);
// const decodedDAta = atob(encodedData);

//jwt.sign(payload, secretkey, [options, callback])
//jwt.verify(token, secretkey, [options, callback])
//"Your secret key should be stored in an environment variable"