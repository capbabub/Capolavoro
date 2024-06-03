package view;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

//La classe FrameLibro rappresenta la finestra per visualizzare i dettagli di un libro
public class FrameLibro extends JFrame {
	private JPanel contentPane;
		private JLabel lblTitolo;
	    private JPanel panelCenter;
	    private JLabel lblPreAutore;
	    private JLabel lblPrePublisher;
	    private JLabel lblPrezzo;
	    private JLabel lblGenere;
	    private JLabel lblStelle;
	    private JLabel lblNumStelle;
	    private JLabel lblNumPrezzo;
	    private JLabel lblAutore;
	    private JLabel lblPublisher;
	    private JLabel lblDescrizione;
	    private JButton btnCarrello;
	    private int IDLib;
	

	    public FrameLibro(FrameLibroInterfaccia listener) {
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setPreferredSize(new Dimension(450, 300));
	        contentPane = new JPanel();
	        contentPane.setBackground(Color.WHITE);
	        contentPane.setBorder(new LineBorder(new Color(255, 255, 255), 8, true));
	        setContentPane(contentPane);
	        contentPane.setLayout(new BorderLayout(0, 0));
	        setResizable(false);

	        lblTitolo = new JLabel("New label");
	        lblTitolo.setBackground(Color.WHITE);
	        lblTitolo.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
	        contentPane.add(lblTitolo, BorderLayout.NORTH);
	        
	        panelCenter = new JPanel();
	        panelCenter.setBackground(Color.WHITE);
	        contentPane.add(panelCenter);
	        panelCenter.setLayout(new GridLayout(6, 2, 4, 0));
	        	        
	        	        	        lblAutore = new JLabel("New label");
	        	        	        lblAutore.setBackground(Color.WHITE);
	        	        	        panelCenter.add(lblAutore);
	        
	        	        lblPublisher = new JLabel("New label");
	        	        lblPublisher.setBackground(Color.WHITE);
	        	        panelCenter.add(lblPublisher);
	        
	        	        lblDescrizione = new JLabel("New label");
	        	        lblDescrizione.setBackground(Color.WHITE);
	        	        panelCenter.add(lblDescrizione);
	        

	        lblGenere = new JLabel("New label");
	        lblGenere.setBackground(Color.WHITE);
	        panelCenter.add(lblGenere);
	        
	        lblNumPrezzo = new JLabel("New label");
	        lblNumPrezzo.setBackground(Color.WHITE);
	        panelCenter.add(lblNumPrezzo);

	        lblNumStelle = new JLabel("New label");
	        lblNumStelle.setBackground(Color.WHITE);
	        panelCenter.add(lblNumStelle);
	        
	        // Bottone per aggiungere o rimuovere il libro dal carrello
	        btnCarrello = new JButton("Aggiungi al carrello");
	        btnCarrello.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Richiama il metodo getIDLib() quando il pulsante btnCarrello viene premuto
	                listener.onCarrelloButtonClick(IDLib);
	            }
	        });
	        contentPane.add(btnCarrello, BorderLayout.SOUTH);
	    }
	
	    
	public String getLblTitolo() {
	    return lblTitolo.getText();
	}

	public void setLblTitolo(String text) {
	    lblTitolo.setText(text);
	}

	public String getLblGenere() {
	    return lblGenere.getText();
	}

	public void setLblGenere(String text) {
	    lblGenere.setText(" "+text);
	}

	public String getLblNumStelle() {
	    return lblNumStelle.getText();
	}

	public void setLblNumStelle(String text) {
	    lblNumStelle.setText(" num stelle: "+text);
	}

	public String getLblNumPrezzo() {
	    return lblNumPrezzo.getText();
	}

	public void setLblNumPrezzo(String text) {
	    lblNumPrezzo.setText(" prezzo: "+text);
	}

	public String getLblAutore() {
	    return lblAutore.getText();
	}

	public void setLblAutore(String text) {
	    lblAutore.setText(" scritto da: "+text);
	}

	public String getLblPublisher() {
	    return lblPublisher.getText();
	}

	public void setLblPublisher(String text) {
	    lblPublisher.setText(" pubblicato da: "+text);
	}

	public String getLblDescrizione() {
		return lblDescrizione.getText();
	}
	
	public int getIDLib() {
		return IDLib;
	}

	public void setIDLib(int iDLib) {
		IDLib = iDLib;
	}
	
    // Metodo per tagliare la descrizione 
	public void setLblDescrizione(String text) {
	    int maxLength = 90; 
	    if (text.length() > maxLength) {
	        lblDescrizione.setText(" "+text.substring(0, maxLength) + "..."); 
	    } else {
	        lblDescrizione.setText(" "+text); 
	    }
	}
	
	  // Metodo per nascondere il bottone di aggiunta al carrello e mostrare "rimuovi dal carrello"
	public void nascondiAggiungi() {
		btnCarrello.setText("rimuovi dal carrello");
		
	}
	

	
}
