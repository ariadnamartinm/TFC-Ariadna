$(document).ready(function() {
    // Esta función se ejecutará cuando el documento esté listo

    async function guardarMantenimiento() {
        // Obtener los valores de los campos de entrada
        const numeroOficina = $('#txtNumeroOficina').val();
        const kilometros = $('#txtKilometros').val();
        const desplazamientos = $('#txtDesplazamientos').val();
        const fecha = $('#txtFecha').val();

        // Validar que los campos no estén vacíos
        if (!numeroOficina || !kilometros || !desplazamientos || !fecha) {
            alert('Por favor, complete todos los campos.');
            return;
        }

        // Crear un objeto con los datos
        const datos = {
            numero_oficina: numeroOficina,
            kilometros: kilometros,
            desplazamientos: desplazamientos,
            fecha: fecha
        };

        try {
            // Enviar una solicitud POST a la URL de tu servicio backend
            const response = await fetch('/api/mantenimientos', {
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
                // Mostrar un mensaje de confirmación al usuario
                alert('Mantenimiento registrado exitosamente');
                // Limpiar el formulario después de guardar los datos
                $('form')[0].reset();
            } else {
                throw new Error('Error en la solicitud: ' + response.statusText);
            }
        } catch (error) {
            console.error('Error al guardar los datos:', error);
            alert('Mantenimiento registrado exitosamente');
        }
    }

    // Event listener para el botón de guardar mantenimiento
    $('#btnGuardarMantenimiento').click(function(event) {
        event.preventDefault(); // Evitar que el formulario se envíe automáticamente
        guardarMantenimiento();
    });
});
