package crud_Java;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		frmGerenciamentoDeEstoque.setTitle("Gerenciamento de Estoque");
		frmGerenciamentoDeEstoque.setBounds(100, 100, 450, 300);
		frmGerenciamentoDeEstoque.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGerenciamentoDeEstoque.getContentPane().setLayout(null);
		
		loginField = new JTextField();
		loginField.setBounds(10, 76, 100, 20);
		frmGerenciamentoDeEstoque.getContentPane().add(loginField);
		loginField.setColumns(10);
		
		JLabel loginLabel = new JLabel("Login:");
		loginLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		loginLabel.setBounds(10, 45, 60, 20);
		frmGerenciamentoDeEstoque.getContentPane().add(loginLabel);
		
		JLabel passLabel_1 = new JLabel("Senha:");
		passLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		passLabel_1.setBounds(11, 107, 70, 20);
		frmGerenciamentoDeEstoque.getContentPane().add(passLabel_1);
		
		JButton entrarButton = new JButton("Entrar");
		entrarButton.setBounds(10, 169, 103, 23);
		frmGerenciamentoDeEstoque.getContentPane().add(entrarButton);
		entrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeCrud home = new HomeCrud();
				home.frmCrudHome.setVisible(true);
				frmGerenciamentoDeEstoque.setVisible(false);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Criar Conta");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CriarConta crConta = new CriarConta();
				crConta.frmGerenciadorDeEstoque.setVisible(true);
			}
										});
		
		btnNewButton_1.setBounds(324, 11, 100, 23);
		frmGerenciamentoDeEstoque.getContentPane().add(btnNewButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 138, 100, 20);
		frmGerenciamentoDeEstoque.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Pesquisar");
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
		frmGerenciamentoDeEstoque.getContentPane().add(verDados);
	}
}
