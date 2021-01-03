// let url = "http://localhost:8080/rest";
//
// function getAllUsers() {
//     fetch(url)
//         .then((response) => {
//             return response.json();
//         })
//         .then((data) => {
//             $("#admin_table").empty();
//             $(data).each(function (user) {
//                 $("#admin_table").append(
//                     $("<tr>").append(
//                         $("<td>").text(user.id),
//                         $("<td>").text(user.name),
//                         $("<td>").text(user.surname),
//                         $("<td>").text(user.age),
//                         $("<td>").text(user.email),
//                         $("<td>").text(user.roles.length == 1 ? user.roles[0]['role'] : user.roles[0]['role'] + ' ' + user.roles[1]['role']),
//
//                         $("<td>").append("<button type='button' data-toggle='modal' class='btn-info btn'" +
//                             "data-target='#editUser' data-user-id='" + user.id + "'>Edit</button>"),
//
//                         $("<td>").append("<button type='button' data-toggle='modal' class='btn btn-danger'" +
//                             "data-target='#deleteUser' data-user-id='" + user.id + "'>Delete</button>")
//                     )
//                 );
//             });
//         });
// }
// getAllUsers();


function getUsers() {
    fetch('http://localhost:8080/rest').then((res) => res.json())
        .then((data) => {
            let output = " ";
            // console.log(data)
            data.forEach(function (user) {

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
                <a class="btn btn-info" role="button"
                data-toggle="modal" data-target="#edit" id="callModalEdit"
                onclick="openModalWindow(${user.id})">Edit</a>
                </td>
                <td>
                <a class="btn btn-danger" role="button"
                data-toggle="modal" data-target="#delete" id="delete-post"
                onclick="openModalWindowDel(${user.id})">Delete</a>
                </td>
              </tr>`;
            });
            document.getElementById('getAllUsers').innerHTML = output;
        })
}
getUsers();