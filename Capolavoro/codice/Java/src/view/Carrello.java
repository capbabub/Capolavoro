package view;

import javax.swing.*;

import control.PonteAPI;
import control.UnmarshallerLibri;
import model.Libro;

import java.util.Iterator;
import java.awt.*;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

//La classe Carrello rappresenta la finestra che visualizza i libri nel carrello dell'utente
public class Carrello extends JFrame implements FrameLibroInterfaccia {
	JScrollPane scrollPaneCarrello;
	JLabel lblPrezzoTotale;
	JPanel panelLibri;
	JPanel panelMain;
	BigDecimal prezzoTotale;
	PonteAPI ponte;
	java.util.List<Libro> carrelloLibri = new ArrayList<>();

	public Carrello(PonteAPI ponte, UnmarshallerLibri traduttore, java.util.List<Libro> libri) {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		setBounds(100, 100, 1450, 750);
		panelMain = new JPanel();
		getContentPane().add(panelMain);
		panelMain.setLayout(new BorderLayout(0, 0));
		this.ponte = ponte;
		JPanel panel_1 = new JPanel();
		panelMain.add(panel_1, BorderLayout.SOUTH);

		// Bottone per tornare alla libreria
		JButton btnIndietro = new JButton("Ritorna alla libreria");
		btnIndietro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrontendJFrame frontendFrame = new FrontendJFrame(ponte, traduttore);

				frontendFrame.mantieniCarrello(carrelloLibri);

				// Chiudi l'istanza di Carrello
				dispose();
				// Riapri l'istanza di FrontendJFrame

				frontendFrame.setVisible(true);
			}
		});

		// Aggiungi libri alla List
		for (Libro libro : libri) {
			carrelloLibri.add(libro);
		}

		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		panel_1.add(btnIndietro);

		// Bottone per acquistare i libri nel carrello
		JButton btnAcquista = new JButton("Acquista");
		btnAcquista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acquista();
			}
		});
		panel_1.add(btnAcquista);

		// JScrollPane per visualizzare i libri nel carrello
		scrollPaneCarrello = new JScrollPane();
		scrollPaneCarrello.getVerticalScrollBar().setUnitIncrement(10);
		panelMain.add(scrollPaneCarrello, BorderLayout.CENTER);

		// JLabel per mostrare il prezzo totale del carrello
		lblPrezzoTotale = new JLabel("New label");
		lblPrezzoTotale.setForeground(new Color(255, 0, 0));
		lblPrezzoTotale.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblPrezzoTotale.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPaneCarrello.setColumnHeaderView(lblPrezzoTotale);

		// Visualizza i libri nel carrello
		visualizzaCarrello();
	}

	public void apriCarrello() {
		setVisible(true);
	}

	// Metodo per visualizzare i libri nel carrello
	public void visualizzaCarrello() {
		prezzoTotale = BigDecimal.ZERO;
		panelLibri = new JPanel(); // Crea un nuovo pannello per contenere i libri
		panelLibri.setLayout(new GridLayout(0, 2, 10, 10));
		// Itera sulla lista di libri
		for (int i = 0; i < carrelloLibri.size(); i++) {
			Libro libro = carrelloLibri.get(i); // Ottieni il libro corrente
			FrameLibro frameLibro = new FrameLibro(this); // Crea una nuova istanza di FrameLibro per il singolo libro

			// Imposta i dettagli del libro nel FrameLibro
			frameLibro.setLblTitolo(libro.getTitolo());
			frameLibro.setLblAutore(libro.getAutore());
			frameLibro.setLblPublisher(libro.getPublisher());
			frameLibro.setLblNumPrezzo(String.valueOf(libro.getPrezzo()));
			frameLibro.setLblGenere(libro.getGenere());
			frameLibro.setLblNumStelle(String.valueOf(libro.getRecensione()));
			frameLibro.setLblDescrizione(libro.getDescrizione());
			frameLibro.setIDLib(libro.getIsbn());
			frameLibro.nascondiAggiungi();
			prezzoTotale = prezzoTotale.add(libro.getPrezzo());
			// aggiungi un bordo ad ogni istanza di FrameLibro
			JPanel panelFrameLibro = (JPanel) frameLibro.getContentPane();
			panelFrameLibro.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			// Aggiungi il FrameLibro al pannello principale dei libri
			panelLibri.add(frameLibro.getContentPane());
		}

		// setta il prezzo totale
		lblPrezzoTotale.setText("Prezzo totale: " + prezzoTotale + " euro");
		// Aggiungi il pannello dei libri al JScrollPane
		scrollPaneCarrello.setViewportView(panelLibri);
	}

	// Metodo per eseguire l'acquisto dei libri nel carrello
	public void acquista() {
		prezzoTotale = BigDecimal.ZERO;
		lblPrezzoTotale.setText("Prezzo totale: " + prezzoTotale + " euro");

		// Rimuovi tutti i componenti dal pannello dei libri
		panelLibri.removeAll();
		// Aggiorna la vista
		panelMain.revalidate();
		panelMain.repaint();
		System.out.println("libri acquistati: ");
		for (Libro libri : carrelloLibri) {
			System.out.println(libri.getTitolo());
		}
		ponte.sottraiLibri(carrelloLibri);	// Invia una richiesta per rimuovere i libri dal server
		carrelloLibri.clear();	// Cancella la lista dei libri nel carrello
	}

	// Implementazione del metodo dell'interfaccia per gestire il click sul pulsante nel FrameLibro
	@Override
	public void onCarrelloButtonClick(int IDLib) {
		Iterator<Libro> iterator = carrelloLibri.iterator();
		while (iterator.hasNext()) {
			Libro libro = iterator.next();
			// Rimuovi l'elemento corrente dalla lista tramite l'iteratore
			if (libro.getIsbn() == IDLib) {
				iterator.remove(); 
				break;
			}
		}
		visualizzaCarrello();// Aggiorna la visualizzazione del carrello dopo la rimozione del libro
	}

}
