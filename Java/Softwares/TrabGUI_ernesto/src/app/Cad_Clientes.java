package app;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Cad_Clientes {

	private JFrame frmGestorImoveis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cad_Clientes window = new Cad_Clientes();
					window.frmGestorImoveis.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cad_Clientes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestorImoveis = new JFrame();
		frmGestorImoveis.setTitle("Gestor Imoveis | Cadastro de Clientes");
		frmGestorImoveis.setBounds(100, 100, 600, 413);
		frmGestorImoveis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private static String getAddressFromCEP(String cep) throws IOException {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse a resposta JSON para obter os dados de endereço
            // Neste exemplo, estou supondo que a resposta JSON é um objeto com campos 'logradouro', 'bairro', 'localidade', etc.
            return response.toString(); // Aqui você precisa implementar o parsing adequado dos campos JSON
        } else {
            throw new IOException("Erro na consulta de CEP. Código de resposta: " + responseCode);
        }
    }
}
