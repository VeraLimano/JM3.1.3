document.getElementById("deleteModal").addEventListener("submit", deleteUser)

function deleteUser(e) {
    e.preventDefault();

    let id = document.getElementById("id3").value;

    fetch("http://localhost:8080/usersDelete/" + id, {
        method: "DELETE"
    })
        .finally(() => {
            document.getElementById("delete").click();
            getAll();
            document.getElementById("deleteModal").reset();
        })

}

function openModalWindowDel(id) {
    document.getElementById("id3").value = id;
    document.getElementById("name3").value = $("#name" + id).text();
    document.getElementById("surname3").value = $("#lastName" + id).text();
    document.getElementById("age3").value = $("#age" + id).text();
    document.getElementById("email3").value = $("#email" + id).text();
    document.getElementById("password3").value = "";
}

