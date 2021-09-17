/*@author Karish Resodikromo
* Javascript for welcomepage after login*/

window.addEventListener('load',main)

function main() {

    $.ajax({
            type: "GET",
            url: 'http://localhost:8080/welcome/get/json',
            headers: {"Authorization": localStorage.getItem('Authentication')},
            contentType: "application/json",
            accepts: { json: "application/json"},
            success: function (data) {
                console.log(data);
                var myInfo = JSON.parse(data);
                var role = myInfo.role;
                var username = myInfo.username;
                checkRole(role);
                alert("Hello " + username);
            },
            fail: function (role, username, errorThrown) {
                console.log(role);
                console.log(username);
                console.log(errorThrown);
                alert('request failed');
            }
        })


    function checkRole(role) {
        switch (role) {
            case "client":
                $("div.client").show();
                $("div.navplacement").load("../navbarClient/navbarClient.html");
                break;
            case "administrator":
                $("div.administrator").show();
                $("div.navplacementAdmin").load("../navbarAdmin/navbarAdmin.html");
                break;
            default:
                console.log('This is not a valid user role');
        }
    }
}