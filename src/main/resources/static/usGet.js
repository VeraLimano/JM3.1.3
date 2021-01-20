function getUser() {

    fetch("http://localhost:8080/userGet").then((res) => res.json())
        .then((user) => {
            let userRoles = " ";
            for (let i = 0; i < user.roles.length; i++) {
                userRoles += `${user.roles[i].role} `
            }

            let output = "<tr>";
            output += `
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${userRoles}</td>
            `;
            output += "<tr>";

            document.getElementById("gUser").innerHTML = output;
        })
}

function nameTable() {
    fetch("http://localhost:8080/userGet").then((response) => response.json())
        .then((user) => {
            let userRoles = " ";
            for (let i = 0; i < user.roles.length; i++) {
                userRoles += `${user.roles[i].role} `
            }

            let output = `${user.name}` + " with roles: " + userRoles;

            document.getElementById("str").innerHTML = output;
        })
}

nameTable();
getUser();