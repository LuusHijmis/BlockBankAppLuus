window.addEventListener('load',main)

function main() {

    $.ajax({
            type: "GET",
            url: 'http://localhost:8080/welcome/get/json',
            header: {"Authorization": JSON.stringify(localStorage.getItem('Authentication'))},
            // contentType: "application/json",
            dataType: json,
            accepts: {json: "application/json"},
            success: function (data) {
                console.log(data)
                // console.log(role);
                // console.log(username);
                alert("Hello " + data)
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