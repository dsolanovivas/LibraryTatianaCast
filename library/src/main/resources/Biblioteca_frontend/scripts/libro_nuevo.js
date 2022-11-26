function agregarLibro() {
  console.log("agregar libro");

  let nuevoLibroForm = document.forms["agregarLibroForm"];
  let nombre = nuevoLibroForm.nombre.value;
  let autor = nuevoLibroForm.autor.value;
  let categoria = nuevoLibroForm.categoria.value;
  let numeroEjemplar = nuevoLibroForm.numeroEjemplar.value;
  let isbn = nuevoLibroForm.isbn.value;
  let edicion = nuevoLibroForm.edicion.value;
  let descripcion = nuevoLibroForm.descripcion.value;
  let foto = nuevoLibroForm.foto.files[0];
  let archivoImagen = nuevoLibroForm.foto.value;
  const editorial = "Panamericana";

  console.log(nombre);
  console.log(autor);
  console.log(categoria);
  console.log(numeroEjemplar);
  console.log(isbn);
  console.log(edicion);
  console.log(descripcion);
  console.log(foto);

  var requestOptions = {
    method: 'POST',
    redirect: 'follow'
  };
  let urlGuardarLibro = "http://localhost:8080/Libro/guardarLibro/1/" + categoria + "/" + nombre + "/" + isbn + "/" + descripcion + "/" + autor + "/" + foto.name + "/" + numeroEjemplar + "/" + edicion + "/" + editorial;
  console.log(urlGuardarLibro);

  fetch(urlGuardarLibro, requestOptions)
    .then(response => response.text())
    .then(result => {
      console.log(result)
      let idLibro = result;

      var formdata = new FormData();
      formdata.append("idLibro", idLibro);
      formdata.append("idBibliotecario", "1");
      formdata.append("foto", foto, archivoImagen);

      var requestOptions = {
        method: 'POST',
        body: formdata,
        redirect: 'follow'
      };

      fetch("http://localhost:8080/Libro/guardarFotoLibro", requestOptions)
        .then(response => response.text())
        .then(result => {
          console.log(result);
          alert("El libro se agregó exitosamente");
          limpiarCampos();
        })
        .catch(error => console.log('error', error));

    })
    .catch(error => console.log('error', error));

};

function limpiarCampos(){
  document.getElementById("inputNombreLibro").value = "";
  document.getElementById("inputNombreAutor").value = "";
  document.getElementById("inputNumeroCategoria").value = "";
  document.getElementById("inputNumeroEjemplares").value = "";
  document.getElementById("inputNumeroISBN").value = "";
  document.getElementById("inputNumeroEdicion").value = "";
  document.getElementById("inputDescripcion").value = "";
  document.getElementById("inputFoto").value = "";
}