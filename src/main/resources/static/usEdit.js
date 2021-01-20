document.getElementById("editModal").addEventListener("submit", edit);

function edit(e) {
    e.preventDefault();

    let rolesU = [];
    let idU = document.getElementById("id4").value;
    let nameU = document.getElementById("name4").value;
    let surnameU = document.getElementById("surname4").value;
    let ageU = document.getElementById("age4").value;
    let emailU = document.getElementById("email4").value;
    let passwordU = document.getElementById("password4").value;
    rolesU = setRoles(Array.from(document.getElementById("role4").selectedOptions)
        .map(option => option.value));

    console.log(rolesU)

    fetch("http://localhost:8080/usersEdit", {
        method: "PUT",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify({
            id: idU,
            name: nameU,
            surname: surnameU,
            age: ageU,
            email: emailU,
            password: passwordU,
            roles: rolesU

        })
    })
        .finally(() => {
            document.getElementById("edit").click();
            getAll();
            document.getElementById("editModal").reset();
        })
}

function openModalWindow(id) {
    document.getElementById("id4").value = id;
    document.getElementById("name4").value = $("#name" + id).text();
    document.getElementById("surname4").value = $("#lastName" + id).text();
    document.getElementById("age4").value = $("#age" + id).text();
    document.getElementById("email4").value = $("#email" + id).text();
    document.getElementById("password4").value = $("#password" + id).val();
}