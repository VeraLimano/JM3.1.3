document.getElementById("addUser").addEventListener("submit", add);

function add(e) {
    e.preventDefault();

    let roles = [];
    let nameU = document.getElementById("nameAdd").value;
    let surnameU = document.getElementById("surnameAdd").value;
    let ageU = document.getElementById("ageAdd").value;
    let emailU = document.getElementById("emailAdd").value;
    let passwordU = document.getElementById("passwordAdd").value;
    roles = setRoles(Array.from(document.getElementById("roleAdd").selectedOptions)
        .map(option => option.value));

    console.log(roles)

    fetch("http://localhost:8080/usersAdd", {
        method: "POST",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify({
            name: nameU,
            surname: surnameU,
            age: ageU,
            email: emailU,
            password: passwordU,
            roles: roles

        })
    })
        .finally(() => {
            document.getElementById("start").click();
            getAll();
            document.getElementById("addUser").reset();
        })
}

function setRoles(someRoles) {
    let roles = [];
    if (someRoles.indexOf("ROLE_ADMIN") >= 0) {
        roles.push({"id": 1, "name": "ROLE_USER"});
    }
    if (someRoles.indexOf("ROLE_USER") >= 0) {
        roles.push({"id": 2, "name": "ROLE_ADMIN"});
    }
    return roles;
}