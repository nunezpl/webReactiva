const eventSource = new EventSource('/api/notas');

eventSource.onmessage = (event) => {
    const nota = JSON.parse(event.data);
    console.log(event.data);
    const newRow = document.createElement("tr");
    newRow.innerHTML = `
        <td>${nota.id}</td>
        <td>${nota.materia_id}</td>
        <td>${nota.profesor_id}</td>
        <td>${nota.estudiante_id}</td>
        <td>${nota.observacion}</td>
        <td>${nota.valor}</td>
        <td>${nota.porcentaje}</td>
        <td>
            <a href="/api/personas/borrar/${nota.id}" class="button2">
                Eliminar
            </a>
        </td>
    `;

    document.getElementById('notas-list').appendChild(newRow);
};

eventSource.onerror = (error) => {
    console.error('Error en la conexión SSE', error);
    eventSource.close();
};
