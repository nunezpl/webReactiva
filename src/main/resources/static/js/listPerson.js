// Define a function to handle DELETE requests
function handleDelete(personId) {
    // Send the DELETE request
    fetch(`/api/personas/borrar/${personId}`, {
        method: "DELETE"
    })
        .then(response => {
            if (response.status === 204) {
                // Successful deletion
                console.log("Person deleted successfully.");
                // Optionally, you can remove the person from the list in the UI
                const rowToRemove = document.getElementById(`person-${personId}`);
                if (rowToRemove) {
                    rowToRemove.remove();
                }
            } else {
                // Handle deletion error
                console.error("Deletion failed. Status code: " + response.status);
            }
        })
        .catch(error => {
            // Handle network or other errors
            console.error("Error during deletion:", error);
        });
}

const eventSource = new EventSource('/api/personas');

eventSource.onmessage = (event) => {
    const persona = JSON.parse(event.data);

    const newRow = document.createElement("tr");
    newRow.id = `person-${persona.id}`;
    newRow.innerHTML = `
        <td>${persona.nombre}</td>
        <td>${persona.apellido}</td>
        <td>${persona.correo}</td>
        <td>${persona.rol}</td>
        <td>
            <a href="/api/personas/${persona.id}" class="button2">
                Detalles
            </a>
            &nbsp;&nbsp;
            <button class="delete-button button2" data-person-id="${persona.id}">
                Eliminar
            </button>
        </td>
    `;

    // Attach a click event handler to the "Delete" button
    newRow.querySelector(".delete-button").addEventListener("click", () => {
        if (confirm("Are you sure you want to delete this person?")) {
            handleDelete(persona.id);
        }
    });

    document.getElementById('personas-list').appendChild(newRow);
};

eventSource.onerror = (error) => {
    console.error('Error en la conexión SSE', error);
    eventSource.close();
};
