async function getAll() {
    fetch('http://localhost:8080/users')
        .then(response => {
            response.json().then(data => {

                let output = " ";
                // console.log(data)
                data.forEach(user => {

                    let userRoles = " ";
                    for (let i = 0; i < user.roles.length; i++) {
                        userRoles += `${user.roles[i].role} `
                    }
                    console.log(userRoles);

                    output += `
                <tr class="font-weight-normal">
                <td id="id${user.id}">${user.id}</td>
                <td id="name${user.id}">${user.name}</td>
                <td id="lastName${user.id}">${user.surname}</td>
                <td id="age${user.id}">${user.age}</td>
                <td id="email${user.id}">${user.email}</td>
                <td id="roles${user.id}">${userRoles}</td>
                <td>
                <button class="btn btn-info" role="button" data-toggle="modal" data-target="#edit" 
                onclick="openModalWindow(${user.id})">Edit</button>
                </td>
                <td>
                <button class="btn btn-danger" role="button" data-toggle="modal" data-target="#delete" 
                onclick="openModalWindowDel(${user.id})">Delete</button>
                </td>
              </tr>`;
                })
                document.getElementById("table").innerHTML = output;
            })
        })
}

getAll();

