package crud_Java;



import connect.LoginController;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class CriarConta {

	public JFrame frmGerenciadorDeEstoque;
	private JTextField nomeField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField loginField;

	/**
	 * Launch the application.
	 */
	private static CriarConta window;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JLabel lblNewLabel_2;
	private JRadioButton rdbtnAuditor;
	private JRadioButton rdbtnNewRadioButton_3;
	private JLabel lblNewLabel_3;
	private JRadioButton rdbtnRepositor;
	private JRadioButton rdbtnFinanceiro;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new CriarConta();
					window.frmGerenciadorDeEstoque.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CriarConta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGerenciadorDeEstoque = new JFrame();
		frmGerenciadorDeEstoque.setTitle("Gerenciador de Estoque | Criar Conta");
		frmGerenciadorDeEstoque.setBounds(100, 100, 450, 300);
		frmGerenciadorDeEstoque.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGerenciadorDeEstoque.getContentPane().setLayout(null);
		
		
		
		
		
		nomeField = new JTextField();
		nomeField.setBounds(66, 21, 140, 20);
		frmGerenciadorDeEstoque.getContentPane().add(nomeField);
		nomeField.setColumns(10);
		
		JLabel loginLabel = new JLabel("Nome:");
		loginLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		loginLabel.setBounds(10, 19, 54, 20);
		frmGerenciadorDeEstoque.getContentPane().add(loginLabel);
		
		JLabel passLabel_1 = new JLabel("Senha:");
		passLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		passLabel_1.setBounds(10, 118, 70, 20);
		frmGerenciadorDeEstoque.getContentPane().add(passLabel_1);
		
		emailField = new JTextField();
		emailField.setBounds(66, 55, 140, 20);
		frmGerenciadorDeEstoque.getContentPane().add(emailField);
		emailField.setColumns(10);
		
		

		
		JLabel lblNewLabel = new JLabel("Email:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 55, 60, 14);
		frmGerenciadorDeEstoque.getContentPane().add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(66, 120, 140, 20);
		frmGerenciadorDeEstoque.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(66, 151, 140, 20);
		frmGerenciadorDeEstoque.getContentPane().add(passwordField_1);
		
		JLabel lblNewLabel_1 = new JLabel("Login:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 80, 60, 27);
		frmGerenciadorDeEstoque.getContentPane().add(lblNewLabel_1);
		
		loginField = new JTextField();
		loginField.setColumns(10);
		loginField.setBounds(66, 86, 140, 20);
		frmGerenciadorDeEstoque.getContentPane().add(loginField);
		
		JButton btnNewButton_1 = new JButton("Criar Conta");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LoginController Conta = new LoginController();
					Conta.criarConta(CriarConta.this);
				}catch (SQLException sql) {}

				frmGerenciadorDeEstoque.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(66, 196, 140, 23);
		frmGerenciadorDeEstoque.getContentPane().add(btnNewButton_1);
		
		rdbtnNewRadioButton = new JRadioButton("Admin");
		rdbtnNewRadioButton.setBounds(236, 39, 77, 23);
		frmGerenciadorDeEstoque.getContentPane().add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Funcionário");
		rdbtnNewRadioButton_1.setBounds(323, 40, 89, 23);
		frmGerenciadorDeEstoque.getContentPane().add(rdbtnNewRadioButton_1);
		
		lblNewLabel_2 = new JLabel("Privilegio de Acesso:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(226, 21, 124, 14);
		frmGerenciadorDeEstoque.getContentPane().add(lblNewLabel_2);
		
		rdbtnAuditor = new JRadioButton("Auditor");
		rdbtnAuditor.setBounds(236, 101, 77, 23);
		frmGerenciadorDeEstoque.getContentPane().add(rdbtnAuditor);
		
		rdbtnNewRadioButton_3 = new JRadioButton("Conferente");
		rdbtnNewRadioButton_3.setBounds(323, 102, 94, 23);
		frmGerenciadorDeEstoque.getContentPane().add(rdbtnNewRadioButton_3);
		
		lblNewLabel_3 = new JLabel("Cargo:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(226, 80, 124, 14);
		frmGerenciadorDeEstoque.getContentPane().add(lblNewLabel_3);
		
		rdbtnRepositor = new JRadioButton("Repositor");
		rdbtnRepositor.setBounds(236, 127, 77, 23);
		frmGerenciadorDeEstoque.getContentPane().add(rdbtnRepositor);
		
		rdbtnFinanceiro = new JRadioButton("Financeiro");
		rdbtnFinanceiro.setBounds(323, 128, 89, 23);
		frmGerenciadorDeEstoque.getContentPane().add(rdbtnFinanceiro);
	}

	public JTextField getNomeField() {
		return nomeField;
	}

	public void setNomeField(JTextField nomeField) {
		this.nomeField = nomeField;
	}

	public JTextField getEmailField() {
		return emailField;
	}

	public void setEmailField(JTextField emailField) {
		this.emailField = emailField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JPasswordField getPasswordField_1() {
		return passwordField_1;
	}

	public void setPasswordField_1(JPasswordField passwordField_1) {
		this.passwordField_1 = passwordField_1;
	}

	public JTextField getLoginField() {
		return loginField;
	}

	public void setLoginField(JTextField loginField) {
		this.loginField = loginField;
	}
	
	
}
