package crud_Java;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;


import connect.LoginDAO;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;

public class crud {

	public JFrame frmGerenciamentoDeEstoque;
	private JTextField loginField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	private static crud window = new crud();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window.frmGerenciamentoDeEstoque.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public crud() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGerenciamentoDeEstoque = new JFrame();
		frmGerenciamentoDeEstoque.setTitle("Crud | Login");
		frmGerenciamentoDeEstoque.setBounds(100, 100, 228, 203);
		frmGerenciamentoDeEstoque.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGerenciamentoDeEstoque.getContentPane().setLayout(null);
	
		
		loginField = new JTextField();
		loginField.setBounds(67, 15, 100, 15);
		frmGerenciamentoDeEstoque.getContentPane().add(loginField);
		loginField.setColumns(10);
		loginField.addKeyListener(new KeyListener() {
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            passwordField.requestFocus();
		        }
		    }

		    public void keyTyped(KeyEvent e) {
		    }

		    public void keyReleased(KeyEvent e) {
		    }
		});


		
		JLabel loginLabel = new JLabel("Login:");
		loginLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		loginLabel.setBounds(10, 11, 60, 20);
		frmGerenciamentoDeEstoque.getContentPane().add(loginLabel);
		
		JLabel passLabel_1 = new JLabel("Senha:");
		passLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		passLabel_1.setBounds(10, 41, 70, 17);
		frmGerenciamentoDeEstoque.getContentPane().add(passLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(67, 41, 100, 20);
		frmGerenciamentoDeEstoque.getContentPane().add(passwordField);
		passwordField.addKeyListener(new KeyListener() {
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            // Lógica para ação de entrar
		            String login = loginField.getText();
		            String senha = String.valueOf(passwordField.getPassword());
		            
		            boolean verificado = false;
		            try {
		                LoginDAO vrLogin = new LoginDAO();
		                verificado = vrLogin.verificarLogin(login, senha);
		            } catch (SQLException sql) {
		            }
		            if (verificado) {
		                HomeCrud entrar = new HomeCrud(loginField.getText());
		                entrar.frmCrudHome.setVisible(true);
		                frmGerenciamentoDeEstoque.setVisible(false);
		            } else {
		                JOptionPane.showMessageDialog(null, "Login ou Senha estão incorretos ", "Falha na verificação", JOptionPane.INFORMATION_MESSAGE);
		            }
		        }
		    }

		    public void keyTyped(KeyEvent e) {
		    }

		    public void keyReleased(KeyEvent e) {
		    }
		});

		
		
		
		/* ----- BOTÕES ----- */
		JButton entrarButton = new JButton("Entrar");
		entrarButton.setBounds(64, 83, 103, 23);
		frmGerenciamentoDeEstoque.getContentPane().add(entrarButton);
		entrarButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String login = loginField.getText();
		        String senha = String.valueOf(passwordField.getPassword());
		        
		      
				boolean verificado = false;
				try {
					//System.out.println(senha);
					LoginDAO vrLogin = new LoginDAO();
					verificado  = vrLogin.verificarLogin(login, senha);
				}catch (SQLException sql) {}
				if(verificado){
					HomeCrud entrar = new HomeCrud(loginField.getText());
					entrar.frmCrudHome.setVisible(true);
					frmGerenciamentoDeEstoque.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Login ou Senha estão incorretos ", "Falha na verificação",JOptionPane.INFORMATION_MESSAGE );
				}
		    }
		});

		
		JButton btnNewButton_1 = new JButton("Criar Conta");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CriarConta crConta = new CriarConta();
				crConta.frmGerenciadorDeEstoque.setVisible(true);}
										});
		
		btnNewButton_1.setBounds(64, 117, 100, 23);
		frmGerenciamentoDeEstoque.getContentPane().add(btnNewButton_1);

		
		/*JButton btnNewButton = new JButton("Pesquisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pesquisar pesquisa = new Pesquisar();
				pesquisa.frmCrudPesquisar.setVisible(true);
			}
										});
		btnNewButton.setBounds(10, 11, 89, 23);
		frmGerenciamentoDeEstoque.getContentPane().add(btnNewButton);
		
		JButton verDados = new JButton("Vizualizar Dados");
		verDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VerDados dados = new VerDados();
				dados.frmDadosCadastrados.setVisible(true);
				
			}});
		verDados.setBounds(142, 11, 121, 23);
		frmGerenciamentoDeEstoque.getContentPane().add(verDados);*/
	}
}
