package app;

import conexao.Imoveis_DAO;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;



import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

public class Gestor_Imoveis {

	public static JFrame frmGestorImoveis;
	private JTextField loginfield;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestor_Imoveis window = new Gestor_Imoveis();
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
	public Gestor_Imoveis() {
		initialize();
	}

	private void initialize() {
		frmGestorImoveis = new JFrame();
		frmGestorImoveis.getContentPane().setBackground(new Color(204, 0, 51));
		frmGestorImoveis.setTitle("Gestor Imoveis | Login");
		frmGestorImoveis.setBounds(100, 100, 518, 386);
		frmGestorImoveis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestorImoveis.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 153));
		panel.setBorder(new TitledBorder(null, "Entrar", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(84, 73, 321, 221);
		frmGestorImoveis.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Senha:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(21, 88, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Login:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(21, 43, 46, 20);
		panel.add(lblNewLabel_3);
		
		loginfield = new JTextField();
		loginfield.setColumns(10);
		loginfield.setBounds(77, 44, 168, 20);
		panel.add(loginfield);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(77, 88, 168, 17);
		panel.add(passwordField);
		
		

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEntrar.setBounds(77, 137, 168, 23);
		panel.add(btnEntrar);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Imoveis_DAO vrlogin = new Imoveis_DAO();
				String login = loginfield.getText();
				char[] senha = passwordField.getPassword();
				
				if (login.isEmpty() || senha.length == 0)  {
				    
				    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Campos vazios", JOptionPane.WARNING_MESSAGE);
				}else {
					
					try {
						boolean vr = false;
						vr = vrlogin.verificarLogin(loginfield.getText(), String.valueOf(passwordField.getPassword()));
						if (vr) {
							JOptionPane.showMessageDialog(null, "Login Bem sucedido!", "Operação Concluida", JOptionPane.INFORMATION_MESSAGE);
							Home home = new Home(loginfield.getText());
							home.setVisibilidade(true);
							setVisibilidade(false);
						}else {
							JOptionPane.showMessageDialog(null, "Email ou senha Incorretos!", "Falha no Login", JOptionPane.INFORMATION_MESSAGE);

						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               btnEntrar.doClick();
            }
        });
		JButton btnCriarConta = new JButton("Criar Conta");
		btnCriarConta.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCriarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Criar_Conta novaConta = new Criar_Conta();
				novaConta.setVisibilidade(true);
			}
		});
		btnCriarConta.setBounds(77, 171, 168, 23);
		panel.add(btnCriarConta);
		
		JLabel lblNewLabel = new JLabel("Imobiliaria Solutions");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(52, 27, 374, 29);
		frmGestorImoveis.getContentPane().add(lblNewLabel);
		
	}
	
	public static void resetCampos(boolean b) {
		
	}
	
	public static void setVisibilidade(boolean b) {
		frmGestorImoveis.setVisible(b);
	}
	
	/*private void addComponentListener() {
		frmGestorImoveis.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });
    }
	
	private void resizeComponents() {
        int frameWidth = frmGestorImoveis.getContentPane().getWidth();
        int frameHeight = frmGestorImoveis.getContentPane().getHeight();

        int pWidth = frameWidth -28; 
        int pHeight = frameHeight -280; 
        
        int tWidth = frameWidth -50;
        int tHeight = frameHeight - 170;
        
        addLivroPanel.setBounds(10, 0, pWidth, 104);
        botoesPanel.setBounds(10, 107, pWidth, 46);
        
        listaLivrosPanel.setBounds(10, 155, pWidth, tHeight);
        scrollPane.setBounds(10, 25, tWidth+5, tHeight-30);
        table.setBounds(0, 0, pWidth, pHeight);
        
    }*/
}
