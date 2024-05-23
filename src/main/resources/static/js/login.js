$(document).ready(function() {
   // on ready
});


async function iniciaSesion(email, password) {
  try {
    const response = await fetch('api/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ email, password })
    });

    if (!response.ok) { // Verifica el estado de la respuesta
      const errorData = await response.json();
      throw new Error(errorData.error || 'Error en el inicio de sesión');
    }

    const token = await response.text(); // Extrae el token del cuerpo de la respuesta
    localStorage.setItem('token', token); // Almacena el token
    // ... (redirige a la página principal u otra acción)

  } catch (error) {
    console.error('Error al iniciar sesión:', error);
    alert(error.message); // Muestra un mensaje de error al usuario
  }
}