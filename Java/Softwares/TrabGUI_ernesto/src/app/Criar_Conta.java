package app;

import conexao.Imoveis_DAO;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Criar_Conta {

	private JFrame frmGestorImoveis;
	private JTextField nomeField;
	private JTextField loginField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel lblSenha;
	private JLabel lblEmail;
	private JLabel lblNome;
	private JLabel lblLogin;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Criar_Conta window = new Criar_Conta();
					window.frmGestorImoveis.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Criar_Conta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestorImoveis = new JFrame();
		frmGestorImoveis.getContentPane().setBackground(new Color(255, 0, 51));
		frmGestorImoveis.setTitle("Gestor Imoveis | Criar Conta");
		frmGestorImoveis.setBounds(100, 100, 493, 372);
		frmGestorImoveis.getContentPane().setLayout(null);
		
		nomeField = new JTextField();
		nomeField.setBounds(133, 53, 233, 20);
		frmGestorImoveis.getContentPane().add(nomeField);
		nomeField.setColumns(10);
		
		loginField = new JTextField();
		loginField.setToolTipText("No minimo 6 caracteres. Deve começar com letras ou '_'(underline)");
		loginField.setColumns(10);
		loginField.setBounds(133, 96, 233, 20);
		frmGestorImoveis.getContentPane().add(loginField);
		loginField.addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusLost(FocusEvent e) {
	                String login = loginField.getText();
	                
	                if (login.length() < 6 || !Character.isLetter(login.charAt(0)) && login.charAt(0) != '_') {
	                    JOptionPane.showMessageDialog(null, "O login deve ter no mínimo 6 caracteres e começar com letra ou '_'.", "Login Inválido", JOptionPane.WARNING_MESSAGE);
	                } else {
	                
	                }
	            }
	        });
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(133, 143, 233, 20);
		frmGestorImoveis.getContentPane().add(emailField);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Deve ter no min. 6 caracteres.");
		passwordField.setBounds(133, 186, 233, 20);
		frmGestorImoveis.getContentPane().add(passwordField);
		passwordField.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String senha = new String(passwordField.getPassword());
	                if (senha.length() < 6) {
	                    JOptionPane.showMessageDialog(null, "A senha deve ter pelo menos 6 caracteres.", "Senha Inválida", JOptionPane.WARNING_MESSAGE);
	                } else {
	                    // Faça algo com a senha válida
	                }
	            }
	        });
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setToolTipText("Confime a Senha");
		passwordField_1.setBounds(133, 211, 233, 20);
		passwordField_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	btnNewButton.doClick();
            }
        });
		frmGestorImoveis.getContentPane().add(passwordField_1);
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setBounds(57, 189, 46, 14);
		frmGestorImoveis.getContentPane().add(lblSenha);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setBounds(57, 149, 46, 14);
		frmGestorImoveis.getContentPane().add(lblEmail);
		
		lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(255, 255, 255));
		lblNome.setBounds(57, 56, 46, 14);
		frmGestorImoveis.getContentPane().add(lblNome);
		
		lblLogin = new JLabel("Login:");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setBounds(57, 99, 46, 14);
		frmGestorImoveis.getContentPane().add(lblLogin);
		
		btnNewButton = new JButton("Criar Conta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Imoveis_DAO conta = new Imoveis_DAO();
				
				String nome = nomeField.getText();
				String login = loginField.getText();
				char[] senha = passwordField.getPassword();
				
				if (login.isEmpty() || senha.length == 0 || nome.isEmpty())  {
				    // Pelo menos um dos campos está vazio
				    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Campos vazios", JOptionPane.WARNING_MESSAGE);
				}else {
					
					try {
						boolean vr = false;
						vr = conta.crConta(nomeField.getText(), loginField.getText(), String.valueOf(passwordField.getPassword()));
						if (vr) {
							JOptionPane.showMessageDialog(null, "Conta Criada Com Sucesso!", "Operação Concluida", JOptionPane.INFORMATION_MESSAGE);
							setVisibilidade(false);
						}else {
							JOptionPane.showMessageDialog(null, "A operação falhou!", "Falha no Login", JOptionPane.INFORMATION_MESSAGE);

						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton.setBounds(133, 251, 233, 23);
		frmGestorImoveis.getContentPane().add(btnNewButton);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setVisibilidade(boolean op) {
		frmGestorImoveis.setVisible(op);
	}
}
