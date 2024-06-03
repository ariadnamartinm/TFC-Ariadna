$(document).ready(function() {
    verificarAutenticacion();
    cargarUsuarios();
    $('#usuarios').DataTable();
    actualizarEmailDelUsuario();
});

function verificarAutenticacion() {
    const token = localStorage.getItem('token');
    if (!token) {
        window.location.href = 'login.html';
    }
}

function actualizarEmailDelUsuario() {
    const email = localStorage.getItem('email');
    const isAdmin = localStorage.getItem('admin') === 'true' ? 'Administrador' : 'Usuario';

    const userInfo = document.getElementById('user-info');
    userInfo.innerHTML = `<span>${email}</span> (${isAdmin})`; // Muestra el email y el estado de administrador
}


async function cargarUsuarios() {
    const request = await fetch('api/usuarios', {
        method: 'GET',
        headers: getHeaders()
    });

    if (request.status === 401) {
        // Si el token no es válido o ha expirado, redirigir a la página de inicio de sesión
        window.location.href = 'login.html';
        return;
    }

    const usuarios = await request.json();

    let listadoHtml = '';
    for (let usuario of usuarios) {
        let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

        let telefonoTexto = usuario.telefono == null ? '-' : usuario.telefono;
        let usuarioHtml = '<tr><td>' + usuario.id + '</td><td>' + usuario.nombre + ' ' + usuario.apellido + '</td><td>'
            + usuario.email + '</td><td>' + telefonoTexto
            + '</td><td>' + botonEliminar + '</td></tr>';
        listadoHtml += usuarioHtml;
    }

    document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
}

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.token}`
    };
}

async function eliminarUsuario(id) {
    if (!confirm('¿Desea eliminar este usuario?')) {
        return;
    }

    const request = await fetch('api/usuarios/' + id, {
        method: 'DELETE',
        headers: getHeaders()
    });

    if (request.status === 401) {
        // Si el token no es válido o ha expirado, redirigir a la página de inicio de sesión
        window.location.href = 'login.html';
        return;
    }

    location.reload()
}