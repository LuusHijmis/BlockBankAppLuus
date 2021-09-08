document.addEventListener('DOMContentLoaded', main)

function main() {

    $.ajax({
            type: "GET",
            url: 'http://localhost:8080/registerDTO',
            contentType: "application/json",
            accepts: {json: "application/json"},
            success: function (role, username) {
                console.log(role);
                console.log(username);
                alert("Hello " + username)
                window.location.replace('http://localhost:8080/static/welcome/welcome.html');
                checkRole(role);
            },
            fail: function (role, username, errorThrown) {
                console.log(role);
                console.log(username);
                console.log(errorThrown);
                alert('request failed');
            }
        })


    function checkRole() {
        switch (role) {
            case "client":
                $("div.client").show();
                $("div.navbarclient").show();
                break;
            case "bank":
                $("div.bank").show();
                $("div.navbarbank").show();
                break;
            case "administrator":
                $("div.administrator").show();
                $("div.navbaradmin").show()
                break;
            default:
                console.log('This is not a valid user role');
        }
    }
}