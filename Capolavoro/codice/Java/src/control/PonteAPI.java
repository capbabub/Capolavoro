package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Libro;

//La classe PonteAPI si occupa della comunicazione con un server attraverso richieste HTTP
public class PonteAPI {
	// StringBuilder per memorizzare la risposta dal server
	public StringBuilder response;

	// Metodo per inviare richieste di select al server e ricevere una risposta
	public String sendToServer(Map<String, Object> parametri) {
		response = new StringBuilder();
		String urlString = ""; // Variabile per memorizzare l'URL completo

		try {
			// URL della pagina PHP che restituisce XML
			URL url = new URL("http://localhost/CapolavoroAPI/LibriAPI.php/");

			// Costruisce l'URL con i parametri
			StringBuilder urlBuilder = new StringBuilder(url.toString());
			urlBuilder.append('?');

			for (Map.Entry<String, Object> entry : parametri.entrySet()) {
				if (urlBuilder.charAt(urlBuilder.length() - 1) != '?') {
					urlBuilder.append('&');
				}
				urlBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append('=')
						.append(URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8"));
			}

			urlString = urlBuilder.toString(); // Memorizza l'URL completo

			// Stampa dell'URL completo per il debugging
						System.out.println("URL completo: " + urlString);
			// Apre una connessione HTTP
			HttpURLConnection conn = (HttpURLConnection) new URL(urlString).openConnection();
			conn.setRequestMethod("GET");

			// Legge i dati dalla risposta del server
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			// Chiude la connessione
			conn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Restituisce la risposta dal server
		return response.toString();
	}

	// Metodo per sottrarre libri dal server
	public void sottraiLibri(List<Libro> libri) {
		StringBuilder isbnList = new StringBuilder();
		for (int i = 0; i < libri.size(); i++) {
			if (i > 0) {
				isbnList.append(",");
			}
			isbnList.append(libri.get(i).getIsbn());
		}

		// Stampa dei valori di isbnList per il debugging
		System.out.println("Valori di isbnList: " + isbnList.toString());

		// Creazione dei parametri per la richiesta HTTP
		HashMap<String, Object> parametri = new HashMap<>();
		parametri.put("criterio", "eliminazione");
		parametri.put("isbnList", isbnList.toString());

		// Stampa dei parametri per il debugging
		System.out.println("Parametri per la richiesta HTTP: " + parametri);

		// Invio della richiesta al server
		String urlString = "";
		try {
			URL url = new URL("http://localhost/CapolavoroAPI/LibriAPI.php/");

			// Costruisce l'URL con i parametri
			StringBuilder urlBuilder = new StringBuilder(url.toString());
			urlBuilder.append('?');

			for (Map.Entry<String, Object> entry : parametri.entrySet()) {
				if (urlBuilder.charAt(urlBuilder.length() - 1) != '?') {
					urlBuilder.append('&');
				}
				urlBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append('=')
						.append(URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8"));
			}

			urlString = urlBuilder.toString(); // Memorizza l'URL completo

			// Stampa dell'URL completo per il debugging
			System.out.println("URL completo: " + urlString);

			// Apre una connessione HTTP
			HttpURLConnection conn = (HttpURLConnection) new URL(urlString).openConnection();
			conn.setRequestMethod("GET");

			// Legge la risposta dal server
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();

			// Stampa della risposta per il debugging
			System.out.println("Risposta dal server: " + response.toString());

			// Chiude la connessione
			conn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
