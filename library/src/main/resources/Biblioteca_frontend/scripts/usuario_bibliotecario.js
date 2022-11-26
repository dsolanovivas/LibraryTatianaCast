getAllLectores();

document.getElementById("barra-busqueda").addEventListener("change", function() {

    let strBusqueda = document.getElementById("barra-busqueda").value;
    if(strBusqueda.trim() == "") {
        getAllLectores();
    }
});

function busquedaMiembro(){
    let strBusqueda = document.getElementById("barra-busqueda").value;

    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
      };
      
      fetch("http://localhost:8080/Miembro/busquedaLectores/" + strBusqueda, requestOptions)
        .then(response => response.text())
        .then(result => {
            reloadTable(result);
        })
        .catch(error => console.log('error', error));

}

function getAllLectores(){
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
      };
      
      fetch("http://localhost:8080/Miembro/allLectores", requestOptions)
        .then(response => response.text())
        .then(result => {
            reloadTable(result);
            })
        .catch(error => console.log('error', error));
};

let funcionBorrarMiembro = function borrarMiembro(){
    let idMiembro = this.id;
    var requestOptions = {
        method: 'POST',
        redirect: 'follow'
      };
      
      fetch("http://localhost:8080/Miembro/borrarMiembro/"+ idMiembro + "/1", requestOptions)
        .then(response => response.text())
        .then(result =>{
            
            getAllLectores();
        })
        .catch(error => console.log('error', error));
};

function reloadTable(miembrosList){
    let listaMiembros = JSON.parse(miembrosList);
            document.getElementById("t-miembros").innerHTML = "";

            let rowListMiembros = document.getElementById("t-miembros");
            listaMiembros.forEach(miembro =>{
                let rowMiembro = document.createElement("tr");

                let tdDni = document.createElement("td");
                tdDni.innerText = miembro.dni;
                let tdNombre = document.createElement("td");
                tdNombre.innerText = miembro.nombre;
                let tdApellidos = document.createElement("td");
                tdApellidos.innerText = miembro.apellidos;
                let tdTelefono = document.createElement("td");
                tdTelefono.innerText = miembro.telefono;
                let tdCorreo = document.createElement("td");
                tdCorreo.innerText = miembro.correo;

                let tdAccion = document.createElement("td");
                let buttonBorrar = document.createElement("button");
                buttonBorrar.className = "btn btn-warning";
                buttonBorrar.id = miembro.idMiembro;
                buttonBorrar.textContent = "";
                buttonBorrar.onclick = funcionBorrarMiembro;
                buttonBorrar.innerHTML =`<i class="bi bi-trash"></i>`;
                tdAccion.appendChild(buttonBorrar);


                rowMiembro.appendChild(tdDni);
                rowMiembro.appendChild(tdNombre);
                rowMiembro.appendChild(tdApellidos);
                rowMiembro.appendChild(tdTelefono);
                rowMiembro.appendChild(tdCorreo);
                rowMiembro.appendChild(tdAccion);
                rowListMiembros.appendChild(rowMiembro);
            })
};