// function getUser() {
//     fetch("http://localhost:8080/userGet")
//         .then(response => {
//             response.json().then(some => {
//                 let one = " ";
//                 console.log(response)
//
//                 let userRoles = " ";
//                 for (let i = 0; i < some.roles.length; i++) {
//                     userRoles += `${some.roles[i].role} `
//                 }
//                 console.log(userRoles);
//
//                 one += `
//                 <tr class="font-weight-normal">
//                 <td>${some.id}</td>
//                 <td>${some.name}</td>
//                 <td>${some.surname}</td>
//                 <td>${some.age}</td>
//                 <td>${some.email}</td>
//                 <td>${userRoles}</td>
//               </tr>`;
//                 document.getElementById("tableOne").innerHTML = one;
//             })
//         })
// }

function getUser() {

    fetch("http://localhost:8080/userGet").then((res) => res.json())
        .then((user) => {
            let userRoles = "";
            for (let i = 0; i < user.roles.length; i++) {
                userRoles += `${user.roles[i].name} `
            }

            let output = "<tr>";
            output += `
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${userRoles}</td>
            `;
            output += "<tr>";

            document.getElementById("gUser").innerHTML = output;
        })
}

getUser();