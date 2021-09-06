//@author hannahvd

function hidePassword() {
    let password = document.getElementById("password");
    if (password.type === "password") {
        password.type = "text";
    } else {
        password.type = "password";
    }
}

//is dit node.js...? //tokenbizniz
document.addEventListener('DOMContentLoaded', () => { //The DOMContentLoaded event fires when the initial HTML document has been completely loaded and parsed, without waiting for stylesheets, images, and subframes to finish loading.
    document.getElementById('submit').addEventListener('click', sendReq);
    sessionStorage.setItem('userJwt', JSON.stringify('bla')); //?????? klopt nie
});

let sendReq = (ev) => { //wat is ev
    let url = 'https://localhost:8080/blockbank/login'; //is dit de juiste url?
    //let userJwt = JSON.parse(<serverding>.getItem('userJwt'));
    let token = JSON.parse(sessionStorage.getItem('userJwt')); //?is dit voor al-bestaande token (niet voor login/nieuw gegenereerd)?

    let h = new Headers();
    h.append('Authorization', `Bearer${token}`); //wat moet 'name' zijn? wat moet 'value' zijn?
        //authentication type is which algorithm will be used for authentication. common type is "basic"
        //"bearer tokens to access Oauth 2.0 protected resources"
        //append() = not compatibility with browsers internet explorer + firefox for android

    let request = new Request(url, { //kan ook init*
        method: 'GET',
        mode: 'cors', //cors, no-cors or same-origin
        headers: h
    });

    fetch(request) // *init. fetch(resource [, init]) //used for [sending?] credentials for http authentication entries
        .then(resp => resp.json())
        .then(data => {
            console.log(data[0]); //hoezo [0]? //is dit het uitvoeren van de token..?
        })
        .catch(err => {
            console.error(err.message);
        })
}


















// The btoa() method creates a Base64-encoded ASCII string from a binary string
//      (i.e., a String object in which each character in the string is treated as a byte of binary data).

// You can use this method to encode data which may otherwise cause communication problems, transmit it,
//     then use the atob() method to decode the data again.

//btoa() only encodes Strings of one (1) byte (dafuk)

let jwtUser = 'bla';
const encodedData = btoa(jwtUser);
const decodedDAta = atob(encodedData);

//jwt.sign(payload, secretkey, [options, callback])
//jwt.verify(token, secretkey, [options, callback])
//"Your secret key should be stored in an environment variable"