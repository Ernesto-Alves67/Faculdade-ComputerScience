package crud_Java;

import java.awt.EventQueue;
import crud_Java.crud;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeCrud {

	public JFrame frmCrudHome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeCrud window = new HomeCrud();
					window.frmCrudHome.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeCrud() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrudHome = new JFrame();
		frmCrudHome.setTitle("Crud | Home");
		frmCrudHome.setBounds(100, 100, 450, 300);
		//frmCrudHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrudHome.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seja Bem-Vindo");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 138, 20);
		frmCrudHome.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Sair\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCrudHome.setVisible(false);
				crud telaInicial = new crud();
				telaInicial.frmGerenciamentoDeEstoque.setVisible(true);
			}
		});
		btnNewButton.setBounds(335, 8, 89, 23);
		frmCrudHome.getContentPane().add(btnNewButton);
	}
}
