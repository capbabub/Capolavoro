package view;

import javax.swing.*;
import javax.swing.border.*;

import control.PonteAPI;
import control.UnmarshallerLibri;
import jakarta.xml.bind.JAXBException;
import model.Libro;
import view.FrameLibro;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class FrontendJFrame extends JFrame implements FrameLibroInterfaccia {
	private static final long serialVersionUID = 1L;
	private PonteAPI ponte;
	private UnmarshallerLibri traduttore;
	private JPanel contentPane;
	private JTextField txtCerca;
	private JRadioButton rdbtnFiltroISBN;
	private JRadioButton rdbtnFiltroTitolo;
	private JTextField txtAutore;
	private JTextField txtPublisher;
	private Map<String, Object> parametri = new LinkedHashMap<>();
	private ButtonGroup radioGroup;
	private java.util.List<Libro> libri;
	private java.util.List<Libro> carrelloLibri = new ArrayList<>();

	// Pannello superiore
	private JPanel panelTop;
	private JButton btnRicerca;
	private JButton btnCercaSimili;
	private JLabel lblLogo;

	// Pannello laterale
	private JPanel panelSide;
	private JLabel lblTitleFiltri;
	private JPanel panelFiltro;
	private JPanel panelFiltroStelle;
	private JLabel lblFiltroStelle;
	private JSpinner spinnerStelle;
	private JPanel panelFiltroGenere;
	private JLabel lblFiltroGenere;
	private JComboBox comboBoxGenere;
	private JPanel panelFiltroPrezzo;
	private JLabel lblFiltroPrezzo;
	private JSpinner spinnerPrezzo;
	// ScrollPane per i risultati della ricerca
	private JScrollPane scrollPaneLibri;
	private JButton btnCarrello;
	private JPanel panelPadding_2;
	private JPanel panelPadding_3;
	private JPanel panelPadding_4;
	private JPanel panelPadding_5;
	private JPanel panelPadding_6;
	private JPanel panelPadding_7;
	private JPanel panelOrdinaPer;
	private JLabel lblOrdinaPer;
	private JComboBox comboBoxOrdinaPer;
	private JPanel panelPadding_8;
	private JPanel panelPadding_9;
	private JPanel panelFiltroAutore;
	private JCheckBox chckbxFiltroAutore;
	private JPanel panelFiltroStelle_1;
	private JCheckBox chckbxFiltroPublisher;
	private JPanel panelPadding;
	private JPanel panelPadding_1;

	public FrontendJFrame(PonteAPI ponteEsterno, UnmarshallerLibri traduttoreEsterno) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrontendJFrame.class.getResource("/img/BookIcon.png")));
		this.ponte = ponteEsterno;
		this.traduttore = traduttoreEsterno;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1450, 750);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// PANNELLO SUPERIORE
		panelTop = new JPanel();
		panelTop.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(64, 0, 64)));
		panelTop.setBackground(new Color(255, 255, 255));
		contentPane.add(panelTop, BorderLayout.NORTH);
		GridBagLayout gbl_panelTop = new GridBagLayout();
		gbl_panelTop.columnWidths = new int[] { 26, 206, 133, 517, 140, 54, 74, 0, 153, 0 };
		gbl_panelTop.rowHeights = new int[] { 41, 0 };
		gbl_panelTop.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelTop.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panelTop.setLayout(gbl_panelTop);

		btnRicerca = new JButton("-->");
		btnRicerca.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRicerca.setForeground(new Color(128, 0, 0));
		btnRicerca.setBackground(Color.white);
		btnRicerca.setOpaque(true);
		//action listener che crea la query sql, traduce la riposta e fa il suo display
		btnRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!rdbtnFiltroISBN.isSelected() && !rdbtnFiltroTitolo.isSelected()) {
					JOptionPane.showMessageDialog(null, "Seleziona un metodo di ricerca!", "Alert",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					clearParametri();
					setParametri();
					resetValori();
					parametri.put("ricerca", "uguale");
					System.out.println(getParametri());
					ponte.sendToServer(parametri);
					try {
						UnmarshallerLibri.traduciLibri(ponte.response.toString());
						libri = traduttore.getLibri();
						displayLibri(libri);
					} catch (JAXBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon(FrontendJFrame.class.getResource("/img/BookLogo.png")));
		lblLogo.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblLogo = new GridBagConstraints();
		gbc_lblLogo.anchor = GridBagConstraints.EAST;
		gbc_lblLogo.fill = GridBagConstraints.VERTICAL;
		gbc_lblLogo.insets = new Insets(0, 0, 0, 5);
		gbc_lblLogo.gridx = 1;
		gbc_lblLogo.gridy = 0;
		panelTop.add(lblLogo, gbc_lblLogo);
		lblLogo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	System.out.println("Label cliccato!");
            	resetValori();
            }
        });

		txtCerca = new JTextField();
		txtCerca.setForeground(new Color(64, 0, 64));
		txtCerca.setText("cerca...");
		txtCerca.setEditable(true); // Abilita l'editing del testo
		txtCerca.selectAll(); // Seleziona tutto il testo
		GridBagConstraints gbc_txtCerca = new GridBagConstraints();
		gbc_txtCerca.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCerca.insets = new Insets(0, 0, 0, 5);
		gbc_txtCerca.gridx = 3;
		gbc_txtCerca.gridy = 0;
		panelTop.add(txtCerca, gbc_txtCerca);
		txtCerca.setColumns(10);
		GridBagConstraints gbc_btnRicerca = new GridBagConstraints();
		gbc_btnRicerca.anchor = GridBagConstraints.WEST;
		gbc_btnRicerca.insets = new Insets(0, 0, 0, 5);
		gbc_btnRicerca.gridx = 4;
		gbc_btnRicerca.gridy = 0;
		panelTop.add(btnRicerca, gbc_btnRicerca);

		txtCerca.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if (txtCerca.getText().equals("cerca...")) {
					txtCerca.setText("");
				}
			}

			public void focusLost(FocusEvent e) {
				if (txtCerca.getText().isEmpty()) {
					txtCerca.setText("cerca...");
				}
			}
		});

		btnCercaSimili = new JButton("Cerca simili");
		btnCercaSimili.setForeground(new Color(0, 128, 192));
		btnCercaSimili.setBackground(Color.white);
		btnCercaSimili.setOpaque(true);
		//action listener che crea la query sql, traduce la riposta e fa il suo display
		btnCercaSimili.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!rdbtnFiltroISBN.isSelected() && !rdbtnFiltroTitolo.isSelected()) {
					JOptionPane.showMessageDialog(null, "Seleziona un metodo di ricerca!", "Alert",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					clearParametri();
					setParametri();
					resetValori();
					parametri.put("ricerca", "simile");
					System.out.println(getParametri());
					ponte.sendToServer(parametri);
					try {
						UnmarshallerLibri.traduciLibri(ponte.response.toString());
						libri = traduttore.getLibri();
						displayLibri(libri);
					} catch (JAXBException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		GridBagConstraints gbc_btnCercaSimili = new GridBagConstraints();
		gbc_btnCercaSimili.insets = new Insets(0, 0, 0, 5);
		gbc_btnCercaSimili.gridx = 6;
		gbc_btnCercaSimili.gridy = 0;
		panelTop.add(btnCercaSimili, gbc_btnCercaSimili);

		btnCarrello = new JButton("Vai al Carrello");
		btnCarrello.setForeground(new Color(64, 0, 128));
		btnCarrello.setBackground(Color.white);
		btnCarrello.setOpaque(true);
		//action listener che crea un'istanza di carrello e la apre
		btnCarrello.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Crea un'istanza di Carrello
				Carrello carrello = new Carrello(ponte, traduttore, carrelloLibri);
				// Visualizza il carrello
				carrello.apriCarrello();
				dispose();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridx = 8;
		gbc_btnNewButton.gridy = 0;
		panelTop.add(btnCarrello, gbc_btnNewButton);

		// PANNELLO LATERALE
		// PANNELLO LATERALE
		// PANNELLO LATERALE
		// PANNELLO LATERALE
		// PANNELLO LATERALE
		// PANNELLO LATERALE
		// PANNELLO LATERALE
		// PANNELLO LATERALE
		// PANNELLO LATERALE
		panelSide = new JPanel();
		panelSide.setBackground(new Color(255, 255, 255));
		contentPane.add(panelSide, BorderLayout.WEST);
		panelSide.setLayout(new BoxLayout(panelSide, BoxLayout.Y_AXIS));

		lblTitleFiltri = new JLabel("Cerca per");
		lblTitleFiltri.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTitleFiltri.setForeground(new Color(255, 0, 0));
		lblTitleFiltri.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitleFiltri.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelSide.add(lblTitleFiltri);

		// Pannello dei filtri
		panelFiltro = new JPanel();
		panelFiltro.setBorder(new MatteBorder(0, 5, 1, 5, (Color) new Color(255, 255, 255)));
		panelFiltro.setBackground(new Color(255, 255, 255));
		panelFiltro.setLayout(new GridLayout(0, 1, 0, 0));
		panelSide.add(panelFiltro);

		// Radio buttons per i filtri ISBN e Titolo
		rdbtnFiltroISBN = new JRadioButton("ISBN"); // Modifica 3: Cambio da JRadioButton a JCheckBox
		rdbtnFiltroISBN.setBackground(new Color(255, 255, 255));
		// Aggiunta di listener per bloccare/sbloccare i campi di input
		rdbtnFiltroISBN.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					boolean criterio = false;
					bloccaValori(criterio);
				} else {
					boolean criterio = true;
					bloccaValori(criterio);
				}
			}
		});

		rdbtnFiltroTitolo = new JRadioButton("Titolo");
		rdbtnFiltroTitolo.setBackground(new Color(255, 255, 255));
		panelFiltro.add(rdbtnFiltroISBN);
		panelFiltro.add(rdbtnFiltroTitolo);

		panelFiltroAutore = new JPanel();
		panelFiltroAutore.setBorder(new MatteBorder(5, 0, 0, 5, (Color) new Color(255, 255, 255)));
		panelFiltroAutore.setBackground(Color.WHITE);
		panelFiltro.add(panelFiltroAutore);
		panelFiltroAutore.setLayout(new GridLayout(0, 2, 0, 0));

		chckbxFiltroAutore = new JCheckBox("Autore");
		chckbxFiltroAutore.setBackground(Color.WHITE);
		panelFiltroAutore.add(chckbxFiltroAutore);
		txtAutore = new JTextField();
		panelFiltroAutore.add(txtAutore);
		txtAutore.setPreferredSize(new Dimension(0, 0));
		txtAutore.setVisible(false);

		panelPadding_1 = new JPanel();
		panelPadding_1.setBackground(Color.WHITE);
		panelFiltroAutore.add(panelPadding_1);

		panelFiltroStelle_1 = new JPanel();
		panelFiltroStelle_1.setBorder(new MatteBorder(5, 0, 0, 0, (Color) new Color(255, 255, 255)));
		panelFiltroStelle_1.setBackground(Color.WHITE);
		panelFiltro.add(panelFiltroStelle_1);
		panelFiltroStelle_1.setLayout(new GridLayout(0, 2, 0, 0));

		chckbxFiltroPublisher = new JCheckBox("Publisher");
		chckbxFiltroPublisher.setBackground(Color.WHITE);
		panelFiltroStelle_1.add(chckbxFiltroPublisher);
		txtPublisher = new JTextField();
		panelFiltroStelle_1.add(txtPublisher);
		txtPublisher.setPreferredSize(new Dimension(0, 0));
		txtPublisher.setVisible(false);

		chckbxFiltroAutore.addActionListener(e -> {
			txtAutore.setVisible(chckbxFiltroAutore.isSelected());
		});

		chckbxFiltroPublisher.addActionListener(e -> {
			txtPublisher.setVisible(chckbxFiltroPublisher.isSelected());
		});

		panelPadding = new JPanel();
		panelPadding.setBackground(Color.WHITE);
		panelFiltroStelle_1.add(panelPadding);

		radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnFiltroISBN);
		radioGroup.add(rdbtnFiltroTitolo);

		panelFiltroStelle = new JPanel();
		panelFiltroStelle.setBorder(new MatteBorder(5, 0, 0, 0, (Color) new Color(255, 255, 255)));
		panelFiltroStelle.setBackground(Color.WHITE);
		panelFiltro.add(panelFiltroStelle);
		panelFiltroStelle.setLayout(new GridLayout(0, 2, 0, 0));

		lblFiltroStelle = new JLabel("  Stelle");
		lblFiltroStelle.setBackground(new Color(255, 255, 255));
		panelFiltroStelle.add(lblFiltroStelle);

		spinnerStelle = new JSpinner();
		spinnerStelle.setForeground(new Color(223, 219, 32));
		spinnerStelle.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		spinnerStelle.setBackground(Color.white);
		spinnerStelle.setOpaque(true);
		JFormattedTextField textFieldStelle = ((JSpinner.DefaultEditor) spinnerStelle.getEditor()).getTextField();
		textFieldStelle.setEditable(false);
		panelFiltroStelle.add(spinnerStelle);

		panelPadding_2 = new JPanel();
		panelPadding_2.setBackground(new Color(255, 255, 255));
		panelFiltroStelle.add(panelPadding_2);

		panelPadding_3 = new JPanel();
		panelPadding_3.setBackground(new Color(255, 255, 255));
		panelFiltroStelle.add(panelPadding_3);

		panelFiltroGenere = new JPanel();
		panelFiltroGenere.setBorder(null);
		panelFiltroGenere.setBackground(Color.WHITE);
		panelFiltro.add(panelFiltroGenere);
		panelFiltroGenere.setLayout(new GridLayout(0, 2, 0, 0));

		lblFiltroGenere = new JLabel("  Genere");
		lblFiltroGenere.setBackground(new Color(255, 255, 255));
		panelFiltroGenere.add(lblFiltroGenere);

		comboBoxGenere = new JComboBox();
		comboBoxGenere.setModel(new DefaultComboBoxModel(new String[] { "/", "Horror", "Romanzo", "Drama", "Fantasy",
				"Commedia", "Giallo", "Per bambini", "Manga", "Scientifico", "Storico", "Biografia", "Distopico" }));
		comboBoxGenere.setBackground(Color.white);
		comboBoxGenere.setOpaque(true);
		panelFiltroGenere.add(comboBoxGenere);

		panelPadding_4 = new JPanel();
		panelPadding_4.setBackground(new Color(255, 255, 255));
		panelFiltroGenere.add(panelPadding_4);

		panelPadding_5 = new JPanel();
		panelPadding_5.setBackground(new Color(255, 255, 255));
		panelFiltroGenere.add(panelPadding_5);

		panelFiltroPrezzo = new JPanel();
		panelFiltroPrezzo.setBorder(null);
		panelFiltroPrezzo.setBackground(new Color(255, 255, 255));
		panelFiltro.add(panelFiltroPrezzo);
		panelFiltroPrezzo.setLayout(new GridLayout(0, 2, 0, 0));

		lblFiltroPrezzo = new JLabel("  Prezzo");
		lblFiltroPrezzo.setBackground(new Color(255, 255, 255));
		panelFiltroPrezzo.add(lblFiltroPrezzo);

		spinnerPrezzo = new JSpinner();
		spinnerPrezzo.setModel(new SpinnerNumberModel(0.0, 0.0, 999.0, 1.0));
		JFormattedTextField textFieldPrezzo = ((JSpinner.DefaultEditor) spinnerPrezzo.getEditor()).getTextField();
		textFieldPrezzo.setEditable(false);
		spinnerPrezzo.setBackground(Color.white);
		spinnerPrezzo.setOpaque(true);
		panelFiltroPrezzo.add(spinnerPrezzo);

		panelPadding_6 = new JPanel();
		panelPadding_6.setBackground(new Color(255, 255, 255));
		panelFiltroPrezzo.add(panelPadding_6);

		panelPadding_7 = new JPanel();
		panelPadding_7.setBackground(Color.WHITE);
		panelFiltroPrezzo.add(panelPadding_7);

		panelOrdinaPer = new JPanel();
		panelOrdinaPer.setBorder(null);
		panelOrdinaPer.setBackground(Color.WHITE);
		panelFiltro.add(panelOrdinaPer);
		panelOrdinaPer.setLayout(new GridLayout(2, 2, 0, 0));

		lblOrdinaPer = new JLabel(" Ordina Per");
		lblOrdinaPer.setBackground(Color.WHITE);
		panelOrdinaPer.add(lblOrdinaPer);

		comboBoxOrdinaPer = new JComboBox();
		comboBoxOrdinaPer
				.setModel(new DefaultComboBoxModel(new String[] { "/", "Prezzo Crescente", "Prezzo Decrescente" }));
		comboBoxOrdinaPer.setMaximumRowCount(3);
		comboBoxOrdinaPer.setBackground(Color.white);
		comboBoxOrdinaPer.setOpaque(true);
		panelOrdinaPer.add(comboBoxOrdinaPer);

		panelPadding_8 = new JPanel();
		panelPadding_8.setBackground(Color.WHITE);
		panelOrdinaPer.add(panelPadding_8);

		panelPadding_9 = new JPanel();
		panelPadding_9.setBackground(Color.WHITE);
		panelOrdinaPer.add(panelPadding_9);

		// ScrollPane per i risultati della ricerca
		scrollPaneLibri = new JScrollPane();
		scrollPaneLibri.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneLibri.getVerticalScrollBar().setUnitIncrement(20);
		contentPane.add(scrollPaneLibri, BorderLayout.CENTER);
	}

	// getter e setter con controllo di input
	public String getTxtCerca() {
		if (txtCerca != null) {
			return txtCerca.getText();
		} else {
			return null;
		}
	}

	public String getAutore() {
		if (txtAutore != null && txtAutore.getText() != "") {
			return txtAutore.getText();
		} else {
			return null;
		}
	}

	public String getPublisher() {
		if (txtPublisher != null && txtPublisher.getText() != "") {
			return txtPublisher.getText();
		} else {
			return null;
		}
	}

	public Integer getRecensione() {
		if (spinnerStelle != null && (Integer) spinnerStelle.getValue() != 0) {
			return (Integer) spinnerStelle.getValue();
		} else {
			return null;
		}
	}

	public String getGenere() {
		if (comboBoxGenere != null && (String) comboBoxGenere.getSelectedItem() != "/") {
			return (String) comboBoxGenere.getSelectedItem();
		} else {
			return null;
		}
	}

	public Double getPrezzo() {
		if (spinnerPrezzo != null && (Double) spinnerPrezzo.getValue() != 0) {
			return (Double) spinnerPrezzo.getValue();
		} else {
			return null;
		}
	}

	public String getOrdinamento() {
		if (comboBoxOrdinaPer != null && (String) comboBoxOrdinaPer.getSelectedItem() != "/") {
			String tipo = (String) comboBoxOrdinaPer.getSelectedItem();
			if (tipo.equalsIgnoreCase("Prezzo Crescente")) {
				return "prezzoCre";
			} else if (tipo.equalsIgnoreCase("Prezzo Decrescente")) {
				return "prezzoDec";
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public Map<String, Object> getParametri() {
		return parametri;
	}

	// metodo per inserire i parametri presi dentro la hashmap
	public void setParametri() {
		if (rdbtnFiltroISBN.isSelected()) {
			parametri.put("isbn", getTxtCerca());
			parametri.put("titolo", "");
		} else if (rdbtnFiltroTitolo.isSelected()) {
			if (getTxtCerca() != null && !getTxtCerca().equals("cerca...")) {
				parametri.put("titolo", getTxtCerca());
			} else {
				parametri.put("titolo", "");
			}

		}
		parametri.put("autore", getAutore() != null ? getAutore() : "");
		parametri.put("publisher", getPublisher() != null ? getPublisher() : "");
		parametri.put("genere", getGenere() != null && !getGenere().equals("/") ? getGenere() : "");
		parametri.put("prezzoLimite", getPrezzo() != null ? getPrezzo() : "");
		parametri.put("recensione", getRecensione() != null ? getRecensione() : "");
		parametri.put("ordinaPer", getOrdinamento() != null ? getOrdinamento() : "");
	}

	// Cancella tutti i parametri impostati per la ricerca
	public void clearParametri() {
		parametri.clear();
	}

	// Reimposta tutti i valori dei campi di input ai valori predefiniti
	public void resetValori() {
		// Resetta il valore dei textfield a ""
		txtCerca.setText("");
		txtAutore.setText("");
		txtPublisher.setText("");
		txtAutore.setVisible(false);
		txtPublisher.setVisible(false);

		// Resetta il valore dei combobox e spinner alla prima opzione di default
		comboBoxGenere.setSelectedIndex(0);
		comboBoxOrdinaPer.setSelectedIndex(0);
		spinnerStelle.setValue(0);
		spinnerPrezzo.setValue(0.0);

		// Resetta lo stato dei checkbox a non selezionato
		chckbxFiltroAutore.setSelected(false);
		chckbxFiltroPublisher.setSelected(false);
		rdbtnFiltroISBN.setSelected(false);
		rdbtnFiltroTitolo.setSelected(false);

	}

	public void bloccaValori(boolean criterio) {
		// Blocca o sblocca tutti i campi di input in base al criterio fornito
		comboBoxGenere.setEnabled(criterio);

		spinnerStelle.setEnabled(criterio);
		spinnerPrezzo.setEnabled(criterio);

		chckbxFiltroAutore.setEnabled(criterio);
		chckbxFiltroPublisher.setEnabled(criterio);

		txtAutore.setEnabled(criterio);
		txtPublisher.setEnabled(criterio);
		comboBoxOrdinaPer.setEnabled(criterio);
	}

	public void displayLibri(java.util.List<Libro> libri) {
		// Visualizza i libri nella GUI
		JPanel panelLibri = new JPanel(); // Crea un nuovo pannello per contenere i libri
		panelLibri.setLayout(new GridLayout(0, 2, 4, 4)); // Imposta un layout a griglia
		panelLibri.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		// Itera sulla lista di libri
		for (Libro libro : libri) {
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
			JPanel panelFrameLibro = (JPanel) frameLibro.getContentPane();

			// Aggiungi il FrameLibro al pannello principale dei libri
			panelLibri.add(frameLibro.getContentPane());
		}

		// Aggiungi il pannello dei libri al JScrollPane
		scrollPaneLibri.setViewportView(panelLibri);
	}

	// Metodo che gestisce quando il pulsante dell'interfaccia FrameLibro viene
	// premuto
	@Override
	public void onCarrelloButtonClick(int IDLib) {
		// Aggiunge un libro al carrello quando il pulsante corrispondente viene premuto
		for (Libro libro : libri) {
			if (libro.getIsbn() == IDLib) {
				carrelloLibri.add(libro);

			}
		}
		// Stampa il contenuto del carrello per debugging
		System.out.println("Contenuto di carrelloLibri:");
		for (Libro libro : carrelloLibri) {
			System.out.println(libro.getTitolo());
		}

	}

	// metodo che salva i contenuti del carrello
	public void mantieniCarrello(java.util.List<Libro> libri) {
		for (Libro libro : libri) {
			carrelloLibri.add(libro);
		}

	}
}
