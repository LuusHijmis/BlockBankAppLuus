window.addEventListener('load',main)

function main() {



    fetch('http://localhost:8080/logout',
        {
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify
        })
        .then(data => {
            localStorage.removeItem('Authentication', data);
            window.location.pathname = '/static/login/login.html';
        })
}

