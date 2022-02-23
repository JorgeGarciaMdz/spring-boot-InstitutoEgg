function bookSelected() {
  const idBook = parseInt(document.getElementById('idBook').value)
  let selectEjemplar = document.getElementById('idEjemplar');

  for (let i = selectEjemplar.length; i >= 0; i--)
    selectEjemplar.remove(i);

  books.forEach(book => {
    if (book.id === idBook) {
      book.ejemplares.forEach(ejemplar => {
        if (ejemplar.available) {
          const opcion = document.createElement('option');
          opcion.value = ejemplar.id;
          opcion.text = ejemplar.location;
          selectEjemplar.appendChild(opcion);
        }
      });
    }
  });
}