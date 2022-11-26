
getAllBooks();


document.getElementById("barra-busqueda-libros-user").addEventListener("change", function() {

    let strBusqueda = document.getElementById("barra-busqueda-libros-user").value;
    if(strBusqueda.trim() == "") {
        getAllBooks();
    }
});


function busquedaBook(){
    let strBusqueda = document.getElementById("barra-busqueda-libros-user").value;

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
            document.getElementById("t-books-user").innerHTML = "";

            let rowListMiembros = document.getElementById("t-books-user");
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

                rowMiembro.appendChild(tdfoto);
                rowMiembro.appendChild(tdNombre);
                rowMiembro.appendChild(tdAutor);
                rowMiembro.appendChild(tdEdicion);
                rowMiembro.appendChild(tdCategoria);
                rowMiembro.appendChild(tdDescripcion);
                rowMiembro.appendChild(tdNumeroEjemplares)
                rowListMiembros.appendChild(rowMiembro);
            })
};

