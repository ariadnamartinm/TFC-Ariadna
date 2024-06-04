$(document).ready(function() {
    // Esta función se ejecutará cuando el documento esté listo

    async function guardarMantenimiento() {
        // Obtener los valores de los campos de entrada
        const numeroOficina = document.getElementById('txtNumeroOficina').value;
        const kilometros = document.getElementById('txtKilometros').value;
        const desplazamientos = document.getElementById('txtDesplazamientos').value;
        const fecha = document.getElementById('txtFecha').value;

        // Crear un objeto con los datos
        const datos = {
            numero_oficina: numeroOficina,
            kilometros: kilometros,
            desplazamientos: desplazamientos,
            fecha: fecha
        };

        try {
            // Enviar una solicitud POST a la URL de tu servicio backend
            const response = await fetch('url_de_tu_servicio_backend', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(datos)
            });

            // Verificar si la respuesta fue exitosa
            if (response.ok) {
                // Convertir la respuesta a JSON
                const data = await response.json();
                console.log('Datos guardados exitosamente:', data);
                // Aquí puedes realizar cualquier acción adicional después de guardar los datos
                alert('Mantenimiento registrado exitosamente');
            } else {
                throw new Error('Error en la solicitud: ' + response.statusText);
            }
        } catch (error) {
            console.error('Error al guardar los datos:', error);
            alert('Error al registrar el mantenimiento');
        }
    }

    // Event listener para el botón de guardar mantenimiento
    $('#btnGuardarMantenimiento').click(function() {
        guardarMantenimiento();
    });
});
