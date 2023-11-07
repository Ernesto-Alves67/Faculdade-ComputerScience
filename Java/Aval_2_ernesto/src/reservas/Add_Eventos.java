package reservas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Add_Eventos {

	private JFrame frmReservaEventos;
	private JTextField nomefield;
	private JTextField datafield;
	private JTextField horafield;
	private JTextField num_ingressosfield;
	private DefaultTableModel tableModel;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Eventos window = new Add_Eventos();
					window.frmReservaEventos.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Add_Eventos() {
		initialize();
	}
	public Add_Eventos(DefaultTableModel tbModel) {
		initialize();
		tableModel = tbModel;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservaEventos = new JFrame();
		frmReservaEventos.setTitle("Reserva Eventos | Adicionar Eventos");
		frmReservaEventos.setBounds(100, 100, 450, 300);
		//frmReservaEventos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReservaEventos.getContentPane().setLayout(null);
		
		JLabel lblnome = new JLabel("Nome do Evento:");
		lblnome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblnome.setBounds(22, 28, 93, 14);
		frmReservaEventos.getContentPane().add(lblnome);
		
		JLabel lbldata = new JLabel("Data:");
		lbldata.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbldata.setBounds(80, 76, 35, 14);
		frmReservaEventos.getContentPane().add(lbldata);
		
		JLabel lblhorario = new JLabel("Horário:");
		lblhorario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblhorario.setBounds(69, 117, 46, 14);
		frmReservaEventos.getContentPane().add(lblhorario);
		
		JLabel lblnum_ingressos = new JLabel("Numeros de Ingressos:");
		lblnum_ingressos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblnum_ingressos.setBounds(10, 157, 147, 14);
		frmReservaEventos.getContentPane().add(lblnum_ingressos);
		
		nomefield = new JTextField();
		nomefield.setBounds(125, 25, 248, 20);
		frmReservaEventos.getContentPane().add(nomefield);
		nomefield.setColumns(10);
		
		datafield = new JTextField();
		datafield.setBounds(125, 73, 248, 20);
		frmReservaEventos.getContentPane().add(datafield);
		datafield.setColumns(10);
		
		horafield = new JTextField();
		horafield.setColumns(10);
		horafield.setBounds(125, 114, 248, 20);
		frmReservaEventos.getContentPane().add(horafield);
		
		num_ingressosfield = new JTextField();
		num_ingressosfield.setColumns(10);
		num_ingressosfield.setBounds(135, 154, 65, 20);
		frmReservaEventos.getContentPane().add(num_ingressosfield);
		
		JButton btnNewButton = new JButton("Finalizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eventos_Disponiveis.addEventos(tableModel, nomefield.getText(), datafield.getText(), horafield.getText(), num_ingressosfield.getText(), textField.getText());
				setVisibilidade(false);
			}
		});
		btnNewButton.setBounds(310, 227, 89, 23);
		frmReservaEventos.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("valor ingresso:");
		lblNewLabel.setBounds(54, 194, 77, 14);
		frmReservaEventos.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(145, 191, 108, 20);
		frmReservaEventos.getContentPane().add(textField);
		textField.setColumns(10);
	}
	
	public void setVisibilidade(boolean b) {
		frmReservaEventos.setVisible(b);
	}
}
