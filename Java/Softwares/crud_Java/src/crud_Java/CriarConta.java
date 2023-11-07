package crud_Java;


import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import connect.LoginController;
import connect.LoginDAO;
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
	private static CriarConta window;
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnFuncionario;
	private JLabel lblNewLabel_2;
	private JRadioButton rdbtnAuditor;
	private JRadioButton rdbtnConferente;
	private JLabel lblNewLabel_3;
	private JRadioButton rdbtnRepositor;
	private JRadioButton rdbtnFinanceiro;
	private String privSelecionada; 
	private String cargSelecionada;
	public String getPrivSelecionada() {
		return privSelecionada;
	}

	public void setPrivSelecionada(String privSelecionada) {
		this.privSelecionada = privSelecionada;
	}

	public String getCargSelecionada() {
		return cargSelecionada;
	}

	public void setCargSelecionada(String cargSelecionada) {
		this.cargSelecionada = cargSelecionada;
	}

	ButtonGroup privilegioGroup = new ButtonGroup();
	ButtonGroup cargoGroup = new ButtonGroup();
	
	/**
	 * Launch the application.
	 */
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
		frmGerenciadorDeEstoque.setTitle("CRUD | Criar Conta");
		frmGerenciadorDeEstoque.setBounds(100, 100, 450, 252);
		//frmGerenciadorDeEstoque.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGerenciadorDeEstoque.getContentPane().setLayout(null);
		
		
		
		
		
		nomeField = new JTextField();
		nomeField.setBounds(66, 21, 140, 15);
		frmGerenciadorDeEstoque.getContentPane().add(nomeField);
		nomeField.setColumns(10);
		
		JLabel loginLabel = new JLabel("Nome:");
		loginLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		loginLabel.setBounds(10, 19, 54, 20);
		frmGerenciadorDeEstoque.getContentPane().add(loginLabel);
		
		JLabel passLabel_1 = new JLabel("Senha:");
		passLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		passLabel_1.setBounds(10, 118, 70, 20);
		frmGerenciadorDeEstoque.getContentPane().add(passLabel_1);
		
		emailField = new JTextField();
		emailField.setBounds(66, 55, 140, 15);
		frmGerenciadorDeEstoque.getContentPane().add(emailField);
		emailField.setColumns(10);
		
		

		
		JLabel lblNewLabel = new JLabel("Email:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 55, 60, 14);
		frmGerenciadorDeEstoque.getContentPane().add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(66, 120, 140, 15);
		frmGerenciadorDeEstoque.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setToolTipText("confirmar senha");
		passwordField_1.setBounds(66, 151, 140, 15);
		frmGerenciadorDeEstoque.getContentPane().add(passwordField_1);
		
		JLabel lblNewLabel_1 = new JLabel("Login:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 80, 60, 27);
		frmGerenciadorDeEstoque.getContentPane().add(lblNewLabel_1);
		
		loginField = new JTextField();
		loginField.setColumns(10);
		loginField.setBounds(66, 86, 140, 15);
		frmGerenciadorDeEstoque.getContentPane().add(loginField);
		
		JButton btnNewButton_1 = new JButton("Criar Conta");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(" "+ getcargoSelection());
				//System.out.println(" "+ getPrivSelection());
				String nome = nomeField.getText();
				String email = emailField.getText();
				String login = loginField.getText();
				String senha = String.valueOf(passwordField.getPassword());
				String senha_1 = String.valueOf(passwordField_1.getPassword());

				if (nome.isEmpty() || email.isEmpty() || login.isEmpty() || senha.isEmpty() || senha_1.isEmpty() || (privilegioGroup.getSelection() == null) || (cargoGroup.getSelection() == null)) {
				    // Pelo menos um dos campos está vazio
				    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Campos vazios", JOptionPane.WARNING_MESSAGE);
				} else {
				    // Todos os campos estão preenchidos
				    // Continuar com o processamento dos dados
					if(rdbtnAdmin.isSelected()) {
						setPrivSelecionada("Admin");
					}else {
						setPrivSelecionada("Funcionario");
					}
					
					if(rdbtnConferente.isSelected()) {
						setCargSelecionada("Conferente");
					}else if(rdbtnAuditor.isSelected()) {
						setCargSelecionada("Auditor");
					}else if(rdbtnRepositor.isSelected()) {
						setCargSelecionada("Repositor");
					}else if(rdbtnFinanceiro.isSelected()) {
						setCargSelecionada("Financeiro");
					}
					
					LoginController Conta = new LoginController();
					boolean vr = false;
					try {
						vr = Conta.criarConta(CriarConta.this);
						if(vr){
							JOptionPane.showMessageDialog(null, "Conta Criada!", "Operação Concluida",JOptionPane.INFORMATION_MESSAGE );
							
						}else {
							JOptionPane.showMessageDialog(null, "Preencha os campos vazios", "Operação Falhou",JOptionPane.INFORMATION_MESSAGE );
						}
						frmGerenciadorDeEstoque.setVisible(false);
					}catch (SQLException sql) {}
					//JOptionPane.showMessageDialog(null, "Conta Criada!", "Operação Concluida",JOptionPane.INFORMATION_MESSAGE );
					frmGerenciadorDeEstoque.setVisible(false);
				}

				
			}
		});
		btnNewButton_1.setBounds(66, 177, 140, 23);
		frmGerenciadorDeEstoque.getContentPane().add(btnNewButton_1);
		
		//define o privilegio da conta a ser criada.
		lblNewLabel_2 = new JLabel("Privilegio de Acesso:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(226, 21, 124, 14);
		frmGerenciadorDeEstoque.getContentPane().add(lblNewLabel_2);
		
		rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rdbtnAdmin.setBounds(236, 39, 77, 23);
		frmGerenciadorDeEstoque.getContentPane().add(rdbtnAdmin);
		
		rdbtnFuncionario = new JRadioButton("Funcionário");
		rdbtnFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rdbtnFuncionario.setBounds(323, 40, 89, 23);
		frmGerenciadorDeEstoque.getContentPane().add(rdbtnFuncionario);

		//Define um cargo para a conta a ser criada
		lblNewLabel_3 = new JLabel("Cargo:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(226, 80, 124, 14);
		frmGerenciadorDeEstoque.getContentPane().add(lblNewLabel_3);
		
		
		rdbtnAuditor = new JRadioButton("Auditor");
		rdbtnAuditor.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rdbtnAuditor.setBounds(236, 101, 77, 23);
		frmGerenciadorDeEstoque.getContentPane().add(rdbtnAuditor);
		
		rdbtnConferente = new JRadioButton("Conferente");
		rdbtnConferente.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rdbtnConferente.setBounds(323, 102, 94, 23);
		frmGerenciadorDeEstoque.getContentPane().add(rdbtnConferente);
		

		rdbtnRepositor = new JRadioButton("Repositor");
		rdbtnRepositor.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rdbtnRepositor.setBounds(236, 127, 77, 23);
		frmGerenciadorDeEstoque.getContentPane().add(rdbtnRepositor);
		
		rdbtnFinanceiro = new JRadioButton("Financeiro");
		rdbtnFinanceiro.setFont(new Font("Tahoma", Font.PLAIN, 10));
		rdbtnFinanceiro.setBounds(323, 128, 89, 23);
		frmGerenciadorDeEstoque.getContentPane().add(rdbtnFinanceiro);
		
		// Crie um ButtonGroup para cada grupo de botões de opção


		// Adicione os botões de opção ao ButtonGroup correspondente
		privilegioGroup.add(rdbtnAdmin);
		privilegioGroup.add(rdbtnFuncionario);
		cargoGroup.add(rdbtnAuditor);
		cargoGroup.add(rdbtnConferente);
		cargoGroup.add(rdbtnRepositor);
		cargoGroup.add(rdbtnFinanceiro);
		
		//privSelecionada = (JRadioButton) privilegioGroup.getSelection();
		//cargSelecionada = (JRadioButton) cargoGroup.getSelection();
		/*if (privilegioGroup.getSelection() != null) {
		    privSelecionada = (JRadioButton) privilegioGroup.getSelection();
		}

		if (cargoGroup.getSelection() != null) {
		    cargSelecionada = (JRadioButton) cargoGroup.getSelection();
		}*/

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
	
	/*public String getPrivSelection() {
		return this.privSelecionada;
	}
	
	public String getcargoSelection() {
		return this.cargSelecionada;
	}*/
}
