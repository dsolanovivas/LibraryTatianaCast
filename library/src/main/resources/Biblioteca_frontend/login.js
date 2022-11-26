function login() {

    let loginForm = document.forms["loginForm"];
    let correo = loginForm.email.value;
    let contrasena = loginForm.password.value;

    console.log(correo, contrasena);

    var requestOptions = {
        method: 'POST',
        redirect: 'follow'
    };

    fetch("http://localhost:8080/Miembro/loginMiembro/" + correo + "/" + contrasena, requestOptions)
        .then(response => response.text())
        .then(result => {
            if (result !== "null") {
                const bibliotecario = 1;
                let usuario = JSON.parse(result);
                console.log(usuario);
                if (usuario.bibliotecario === bibliotecario) {
                    console.log("soy un bibliotecario");
                    window.location.href = "bienvenido_bibliotecario.html";
                } else {
                    console.log("soy un lector");
                    window.location.href = "bienvenido_usuario.html";
                }
            } else {
                alert("usuario o contraseña incorrectos");
            }

        })
        .catch(error => console.log('error', error));
}

function agregarMiembro() {
    let agregarMiembroForm = document.forms["formAgregarUsuario"];
    let dni = agregarMiembroForm.dni.value;
    let nombre = agregarMiembroForm.nombre.value;
    let apellido = agregarMiembroForm.apellidos.value;
    let telefono = agregarMiembroForm.telefono.value;
    let correo = agregarMiembroForm.correo.value;
    let contrasena = agregarMiembroForm.contrasena.value;
    let contrasenav = agregarMiembroForm.contrasenav.value;
    const bibliotecario = 0;

    if (contrasena === contrasenav) {
        var requestOptions = {
            method: 'POST',
            redirect: 'follow'
          };

        let urlGuardarLector = "http://localhost:8080/Miembro/guardarMiembroLector/" + dni + "/" + nombre + "/" + apellido + "/" + telefono + "/" + correo + "/" + contrasena;
        fetch(urlGuardarLector, requestOptions)
            .then(response => response.text())
            .then(result => {
                document.getElementById("dni").value = null;
                document.getElementById("nombre").value = null;
                document.getElementById("apellidos").value = null;
                document.getElementById("telefono").value = null;
                document.getElementById("correo").value = null;
                document.getElementById("contrasena").value = null;
                document.getElementById("contrasenav").value = null;
                alert("Tu cuenta ha sido creada");
            })
            .catch(error => console.log('error', error));
    } else {
        alert("las contraseñas no coinciden")
    }

}