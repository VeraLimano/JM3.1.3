document.getElementById("addNewUser").addEventListener("submit", addNewUser);

function addNewUser(e){
    e.preventDefault();

    let firstName = document.getElementById("nameNew").value;
    let lastName = document.getElementById("surnameNew").value;
    let age = document.getElementById("ageNew").value;
    let email = document.getElementById("emailNew").value;
    let password = document.getElementById("passwordNew").value;
    let roles = setRoles(Array.from(document.getElementById("rolesNew").selectedOptions)
        .map(option => option.value));

    fetch("http://localhost:8088/newUser", {
        method: "POST",
        headers: {
            // "Accept": "application/json, text/plain, */*",
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            name: firstName,
            surname: lastName,
            age: age,
            email: email,
            password: password,
            roles: roles
        })
    })
        .finally(() => {
            document.getElementById("UsersTable").click();
            getUsers();
            document.getElementById("addNewUser").reset();
        })
}

function setRoles(someRoles) {
    let roles = [];
    if (someRoles.indexOf("ROLE_USER") >= 0) {
        roles.push({"id": 1, "name": "ROLE_USER"});
    }
    if (someRoles.indexOf("ROLE_ADMIN") >= 0) {
        roles.push({"id": 2, "name": "ROLE_ADMIN"});
    }
    return roles;
}