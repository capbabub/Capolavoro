<?php
$servername = "localhost";
$username = "root";
$password = "";
$database = "libreria";

// Connessione al database
$conn = new mysqli($servername, $username, $password, $database);

// Verifica della connessione
if ($conn->connect_error) {
    die("Connessione al database fallita: " . $conn->connect_error);
}

$query = "";
//get filtri, se il filtro e vuoto si mette anyString
$isbn = isset($_GET['isbn']) ? $_GET['isbn'] : null;
$titolo = isset($_GET['titolo']) ? $_GET['titolo'] : '%';
$autore = isset($_GET['autore']) ? $_GET['autore'] : '%';
$publisher = isset($_GET['publisher']) ? $_GET['publisher'] : '%';
$recensione = isset($_GET['recensione']) ? $_GET['recensione'] : '';
$prezzo = isset($_GET['prezzoLimite']) ? $_GET['prezzoLimite'] : '';
$genere = isset($_GET['genere']) ? $_GET['genere'] : '%';
$ricerca = isset($_GET['ricerca']) ? ($_GET['ricerca']) : '%';
$criterio = isset($_GET['criterio']) ? ($_GET['criterio']) : '';
$isbnList = isset($_GET['isbnList']) ? ($_GET['isbnList']) : '';
$ordinamento = isset($_GET['ordinaPer']) ? ($_GET['ordinaPer']) : '';

// Costruzione della query 
if ($criterio !== 'eliminazione') {
    if ($ricerca == 'uguale') {
        if ($isbn !== null) {
            // filtro solo per ISBN
            $query = "SELECT * FROM libri WHERE isbn = $isbn";
        } else {
            // filtro per titolo, autore, publisher, recensione, prezzo e genere
            $query = "SELECT * FROM libri WHERE titolo LIKE '%$titolo%' AND autore LIKE '%$autore%' AND publisher LIKE '%$publisher%' AND genere LIKE '%$genere%'";
            //controllo campi prezzo e recensione
            if ($recensione !== '') {
                $query .= " AND recensione = $recensione";
            }
            if ($prezzo !== '') {
                $prezzo = floatval($prezzo);
                $query .= " AND prezzo < $prezzo";
            }
            //ordinamento per prezzo
            if ($ordinamento == "prezzoCre") {
                $query .= " ORDER BY prezzo";
            }elseif ($ordinamento ==  "prezzoDec") {
                $query .= " ORDER BY prezzo desc";
            }
        }
        // Esecuzione della query
        $result = $conn->query($query);
    } else if ($ricerca == 'simile') {
        //Ricerca per simile
        if ($isbn !== null) {
            // filtro solo per ISBN
            $queryGenere = "SELECT * FROM libri WHERE isbn = $isbn";
            $resultGenere = $conn->query($queryGenere);

            // Ottenere il genere dal risultato
            if ($resultGenere->num_rows > 0) {
                $rowGenere = $resultGenere->fetch_assoc();
                $genere = $rowGenere['genere'];
                if ($genere !== null && $genere !== "") {
                    $query = "SELECT * FROM libri WHERE genere = '$genere' AND isbn != $isbn";
                }
                //ordinamento per prezzo
                if ($ordinamento == "prezzoCre") {
                    $query .= " ORDER BY prezzo";
                }elseif ($ordinamento ==  "prezzoDec") {
                    $query .= " ORDER BY prezzo desc";
                }
            }
        }else{
            $query = "SELECT * FROM libri WHERE titolo LIKE '%$titolo%' AND autore LIKE '%$autore%' AND publisher LIKE '%$publisher%' AND genere LIKE '%$genere%'";
            //controllo campi prezzo e recensione
            if ($recensione !== '') {
                $query .= " AND recensione = $recensione";
            }
            if ($prezzo !== '') {
                $prezzo = floatval($prezzo);
                $query .= " AND prezzo < $prezzo";
            }
        }
        // Esecuzione della query
        $result = $conn->query($query);
    }




    // Creazione del documento XML
    $xml = new SimpleXMLElement('<Libri></Libri>');

    // Estrazione dei risultati e inserimento nel documento XML
    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            $libro = $xml->addChild('Libro');
            foreach ($row as $key => $value) {
                $libro->addChild($key, htmlspecialchars($value));
            }
        }
    }
    // Header per indicare che il contenuto è XML
    header('Content-type: text/xml');

    // Stampare il documento XML
    echo $xml->asXML();
} else {

    // Dividi la stringa della lista degli ISBN in un array
    $isbnArray = explode(',', $isbnList);

    // Ottieni il numero di valori nell'array degli ISBN
    $numIsbn = count($isbnArray);

    // Creazione della parte dinamica della query utilizzando implode per unire i valori
    $isbnValues = implode("', '", $isbnArray);
    $isbnValues = "'" . $isbnValues . "'";

    // Creazione della query SQL dinamica
    $query = "UPDATE libri SET numLibri = numLibri - 1 WHERE isbn IN ($isbnValues)";

    // Esegui la query nel database
    $result = $conn->query($query);
    if ($result === true) {
        echo "La query è stata eseguita con successo.";
    } else {
        echo "Si è verificato un errore durante l'esecuzione della query: " . $conn->error;
    }
}
// Chiudere la connessione al database
$conn->close();
?>