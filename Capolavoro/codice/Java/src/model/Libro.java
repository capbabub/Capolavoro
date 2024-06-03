//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v4.0.3 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
//


package model;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per Libro complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>{@code
 * <complexType name="Libro">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="isbn" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="titolo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="autore" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="publisher" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         <element name="recensione">
 *           <simpleType>
 *             <restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               <minInclusive value="1"/>
 *               <maxInclusive value="5"/>
 *             </restriction>
 *           </simpleType>
 *         </element>
 *         <element name="prezzo" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         <element name="genere">
 *           <simpleType>
 *             <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               <enumeration value="Horror"/>
 *               <enumeration value="Romanzo"/>
 *               <enumeration value="Drama"/>
 *               <enumeration value="Fantasy"/>
 *               <enumeration value="Commedia"/>
 *               <enumeration value="Giallo"/>
 *               <enumeration value="Per bambini"/>
 *               <enumeration value="Manga"/>
 *               <enumeration value="Scientifico"/>
 *               <enumeration value="Storico"/>
 *               <enumeration value="Biografia"/>
 *               <enumeration value="Distopico"/>
 *             </restriction>
 *           </simpleType>
 *         </element>
 *         <element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Libro", propOrder = {
    "isbn",
    "titolo",
    "autore",
    "publisher",
    "recensione",
    "prezzo",
    "genere",
    "descrizione"
})
public class Libro {

    protected int isbn;
    @XmlElement(required = true)
    protected String titolo;
    @XmlElement(required = true)
    protected String autore;
    @XmlElement(required = true)
    protected String publisher;
    protected int recensione;
    @XmlElement(required = true)
    protected BigDecimal prezzo;
    @XmlElement(required = true)
    protected String genere;
    @XmlElement(required = true)
    protected String descrizione;

    /**
     * Recupera il valore della proprietà isbn.
     * 
     */
    public int getIsbn() {
        return isbn;
    }

    /**
     * Imposta il valore della proprietà isbn.
     * 
     */
    public void setIsbn(int value) {
        this.isbn = value;
    }

    /**
     * Recupera il valore della proprietà titolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Imposta il valore della proprietà titolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitolo(String value) {
        this.titolo = value;
    }

    /**
     * Recupera il valore della proprietà autore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutore() {
        return autore;
    }

    /**
     * Imposta il valore della proprietà autore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutore(String value) {
        this.autore = value;
    }

    /**
     * Recupera il valore della proprietà publisher.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Imposta il valore della proprietà publisher.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublisher(String value) {
        this.publisher = value;
    }

    /**
     * Recupera il valore della proprietà recensione.
     * 
     */
    public int getRecensione() {
        return recensione;
    }

    /**
     * Imposta il valore della proprietà recensione.
     * 
     */
    public void setRecensione(int value) {
        this.recensione = value;
    }

    /**
     * Recupera il valore della proprietà prezzo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrezzo() {
        return prezzo;
    }

    /**
     * Imposta il valore della proprietà prezzo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrezzo(BigDecimal value) {
        this.prezzo = value;
    }

    /**
     * Recupera il valore della proprietà genere.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenere() {
        return genere;
    }

    /**
     * Imposta il valore della proprietà genere.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenere(String value) {
        this.genere = value;
    }

    /**
     * Recupera il valore della proprietà descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprietà descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

}
