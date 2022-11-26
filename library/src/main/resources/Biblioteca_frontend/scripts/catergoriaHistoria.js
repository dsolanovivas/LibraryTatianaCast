
const urlCargarLibros = "http://localhost:8080/Libro/obtenerLibroCategoria/2";

function cargarLibroCiencias(url) {
    fetch(url)
        .then(function (response) {
            return response.json();

        })

        .then(lista => {
            mostrarLibros(lista);
            // table.innerHTML += "</table>";
        })
}

function mostrarLibros(listaLibros) {
    let table = document.querySelector('#categoria_historia');
    table.innerHTML = "";
    let ct = "";
    for (let cate of listaLibros) {
        console.log(cate)
        ct += `<tr">
        <td>
        <img src="data:image/jpeg;base64,${cate.archivoImagen}" width ="100px">
        </td>
                    <td>${cate.nombre}</td>
                    <td>${cate.autor}</td>
                    <td>${cate.edicion}</td>
                    <td>${cate.idLibro}</td>
                    <td>${cate.idCategoria}</td>
                    <td>${cate.descripcion}</td>
                    <td>${cate.numeroEjemplares}</td>
                    
                    
                </tr>`
    }

    table.innerHTML = ct;
}
cargarLibroCiencias(urlCargarLibros);

function buscarLibro() {
    console.log("buscarLibro");
    let strBusqueda = document.getElementById("busquedaLibroHistoria").value;
    console.log(strBusqueda);
    
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
      };
      
      fetch("http://localhost:8080/Libro/obtenerLibroCategoriaNombre/"+ strBusqueda + "/2", requestOptions)
        .then(response => response.text())
        .then(result => {
            mostrarLibros(JSON.parse(result));
        })
        .catch(error => console.log('error', error));
}