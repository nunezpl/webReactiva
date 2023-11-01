function enviaDatos(event) {
    console.log("Form submitted");
    // Collect form values
    let _nom = document.forms["demo"]["nombre"].value;
    let _ape = document.forms["demo"]["apellido"].value;
    let _cor = document.forms["demo"]["email"].value;
    let _rol = document.forms["demo"]["rol"].value;

    // Validate form input
    if (_nom === "") {
        alert("Debe registrar un nombre");
        return false;
    }
    if (_ape === "") {
        alert("Debe registrar un apellido");
        return false;
    }
    if (_cor === "" || !isValidEmail(_cor)) {
        alert("Debe digitar un correo válido");
        return false;
    }
    if (_rol === "" || (_rol !== "E" && _rol !== "P")) {
        alert("Debe digitar un rol válido");
        return false;
    }

    console.log("Nombre:", _nom);
    console.log("Apellido:", _ape);
    console.log("Correo:", _cor);
    console.log("Rol:", _rol);

    // Create a FormData object with the form data
    let datos = new FormData();
    datos.append("nombre", _nom);
    datos.append("apellido", _ape);
    datos.append("email", _cor);
    datos.append("rol", _rol);

    // Send the FormData object to the server
    fetch("/api/personas/crear", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: datos
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => {
            console.error('Error:', error);
        });

    event.preventDefault();
    return false;
}
document.querySelector("form").addEventListener("submit", enviaDatos);

// Function to check if an email is valid
function isValidEmail(email) {
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
}
