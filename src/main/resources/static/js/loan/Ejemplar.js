function bookSelected() {
  const idBook = parseInt(document.getElementById('idBook').value)
  let selectEjemplar = document.getElementById('idEjemplar');

  for (let i = selectEjemplar.length; i >= 0; i--)
    selectEjemplar.remove(i);

  books.forEach(book => {
    console.log('---- ' + JSON.stringify(book));
    console.log('\n ---- \n ' + (book.id === idBook) + '\n ------');
    console.log(book.id);
    console.log(idBook);
    if (book.id === idBook) {
      book.ejemplares.forEach(ejemplar => {
        console.log("available: " + JSON.stringify(ejemplar))
        if (!ejemplar.available) {
          const opcion = document.createElement('option');
          opcion.value = ejemplar.id;
          opcion.text = ejemplar.location;
          selectEjemplar.appendChild(opcion);
        }
      });
    }
  });
}