
getAllBooks();


document.getElementById("barra-busqueda-libros").addEventListener("change", function() {

    let strBusqueda = document.getElementById("barra-busqueda-libros").value;
    if(strBusqueda.trim() == "") {
        getAllBooks();
    }
});


function busquedaBook(){
    let strBusqueda = document.getElementById("barra-busqueda-libros").value;

    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
      };
      
      fetch("http://localhost:8080/Libro/obtenerLibroNombre/" + strBusqueda, requestOptions)
        .then(response => response.text())
        .then(result => {
            reloadTableBooks(result);
        })
        .catch(error => console.log('error', error));

}



function getAllBooks(){
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
      };
      fetch("http://localhost:8080/Libro/allLibros", requestOptions)
        .then(response => response.text())
        .then(result => {
            reloadTableBooks(result);
            })
        .catch(error => console.log('error', error));
};



function reloadTableBooks(miembrosList){
    let listaMiembros = JSON.parse(miembrosList);
    console.log(listaMiembros)
            document.getElementById("t-books").innerHTML = "";

            let rowListMiembros = document.getElementById("t-books");
            listaMiembros.forEach(miembro =>{
                let rowMiembro = document.createElement("tr");

                let tdfoto = document.createElement("td");
                let imgLibro = document.createElement("img");
                imgLibro.src = "data:image/png;base64," + miembro.archivoImagen;
                imgLibro.style.width = "100px";
                tdfoto.appendChild(imgLibro);
                
                let tdNombre = document.createElement("td");
                tdNombre.innerText = miembro.nombre;
                let tdAutor = document.createElement("td");
                tdAutor.innerText = miembro.autor;
                let tdEdicion = document.createElement("td");
                tdEdicion.innerText = miembro.edicion;
                let tdCategoria = document.createElement("td");
                tdCategoria.innerText = miembro.idCategoria;
                let tdDescripcion = document.createElement("td");
                tdDescripcion.innerText = miembro.descripcion;
                let tdNumeroEjemplares = document.createElement("td");
                tdNumeroEjemplares.innerText = miembro.numeroEjemplares;

                let tdAccion = document.createElement("td");
                let buttonBorrar = document.createElement("button");
                buttonBorrar.className = "btn btn-warning";
                buttonBorrar.id = miembro.idLibro;
                buttonBorrar.textContent = "";
                buttonBorrar.onclick = funcionBorrarMiembro;
                
                buttonBorrar.innerHTML =`<i class="bi bi-trash"></i>`
                tdAccion.appendChild(buttonBorrar);

                rowMiembro.appendChild(tdfoto);
                rowMiembro.appendChild(tdNombre);
                rowMiembro.appendChild(tdAutor);
                rowMiembro.appendChild(tdEdicion);
                rowMiembro.appendChild(tdCategoria);
                rowMiembro.appendChild(tdDescripcion);
                rowMiembro.appendChild(tdNumeroEjemplares)
                rowMiembro.appendChild(tdAccion);
                rowListMiembros.appendChild(rowMiembro);
            })
};

let funcionBorrarMiembro = function borrarMiembro(){
    let idBook= this.id;
    var requestOptions = {
        method: 'POST',
        redirect: 'follow'
      };
      
      fetch("http://localhost:8080/Libro/borrarLibro/"+ idBook + "/1", requestOptions)
        .then(response => response.text())
        .then(result =>{
            
            getAllBooks();
        })
        .catch(error => console.log('error', error));
};