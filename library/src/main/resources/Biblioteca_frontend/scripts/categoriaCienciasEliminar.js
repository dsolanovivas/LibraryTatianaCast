const URL_API = ("http://localhost:8080/Libro/borrarLibro/{{idLibro}}/{{idBibliotecario}}")
async function delete_libro(id){
    const resp = await fetch(`${URL_API}/${id}`,{
        method: "DELETE"
    })
    const text = await resp.text()
    alert(text)
     
}