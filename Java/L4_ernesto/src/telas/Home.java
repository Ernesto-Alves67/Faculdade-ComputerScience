package telas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Home {

	private JFrame frmSgvHome;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frmSgvHome.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSgvHome = new JFrame();
		frmSgvHome.setTitle("SGV | Home");
		frmSgvHome.setBounds(100, 100, 450, 300);
		frmSgvHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSgvHome.getContentPane().setLayout(null);
		
		JButton btnConsultas = new JButton("Consultas");
		btnConsultas.setBounds(0, 0, 163, 25);
		frmSgvHome.getContentPane().add(btnConsultas);
        btnConsultas.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Verifica se o botão está pressionado
                if (btnConsultas.getModel().isPressed()) {
                    // Altera a aparência do botão quando pressionado
                    btnConsultas.setBackground(Color.RED);
                    btnConsultas.setForeground(Color.WHITE);
                } else {
                    // Volta à aparência padrão quando não está pressionado
                    btnConsultas.setBackground(null);
                    btnConsultas.setForeground(null);
                }
            }
        });
		
		JButton btnNada = new JButton("NAda");
		btnNada.setBounds(161, 0, 124, 25);
		frmSgvHome.getContentPane().add(btnNada);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(277, 0, 173, 25);
		frmSgvHome.getContentPane().add(btnSair);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 91, 398, 145);
		frmSgvHome.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBounds(0, 0, 1, 1);
		frmSgvHome.getContentPane().add(table);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(165, 55, 120, 24);
		frmSgvHome.getContentPane().add(comboBox);
		
		comboBox.addItem("Negociações");
		comboBox.addItem("Clientes");
		
		JLabel lblNewLabel = new JLabel("Base de Dados:");
		lblNewLabel.setBounds(34, 55, 113, 15);
		frmSgvHome.getContentPane().add(lblNewLabel);
	}
}
