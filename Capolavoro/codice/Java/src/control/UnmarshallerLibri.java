package control;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import model.Libri;
import model.Libro;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

//La classe UnmarshallerLibri si occupa di convertire una stringa XML in oggetti Libro utilizzando JAXB
public class UnmarshallerLibri {
    private static List<Libro> listaLibri;

    // Metodo per tradurre la stringa XML dei libri in oggetti Libro
    public static void traduciLibri(String libriXML) throws JAXBException {
        // Creazione del contesto JAXB
        JAXBContext jaxbContext = JAXBContext.newInstance("model");

        // Creazione dell'unmarshaller
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        // Converti la stringa XML in un flusso di input
        ByteArrayInputStream inputStream = new ByteArrayInputStream(libriXML.getBytes());

        // Unmarshalling del file XML
        JAXBElement<Libri> jaxbElement = (JAXBElement<Libri>) jaxbUnmarshaller.unmarshal(inputStream);
        Libri libri = jaxbElement.getValue();

        // Creazione di un array per i libri
        listaLibri = libri.getLibro();
    }

    // Metodo per ottenere la lista dei libri tradotti
    public List<Libro> getLibri() {
        return listaLibri;
    }
}