$(document).ready(function() {
    $('#iniciaSesion').click(function(event) {
        event.preventDefault();
        iniciaSesion();
    });
});

async function iniciaSesion() {
    // Obtener los valores de los campos de entrada
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    console.log('Email:', email); // Imprimir el valor de email en la consola
    console.log('Password:', password);

    try {
        const response = await fetch('/api/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.error || 'Error en el inicio de sesión');
        }

        const token = await response.text();
        localStorage.setItem('token', token);
        localStorage.setItem('email',email);
        // Aquí puedes redirigir a la página principal u otra acción si el inicio de sesión es exitoso
        window.location.href = 'usuarios.html';

    } catch (error) {
        console.error('Error al iniciar sesión:', error);
        alert(error.message);
    }
}
