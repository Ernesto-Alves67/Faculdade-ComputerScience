package cad_livro;

import java.awt.EventQueue;

import javax.print.attribute.AttributeSet;
import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Editar {

	private JFrame frmCadastroDeLivros;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField focusedTextField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editar window = new Editar();
					window.frmCadastroDeLivros.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Editar() {}
	public Editar(String editora, String livro, String isbn, String cod, Cad_Livro principal) {
		initialize(editora, livro, isbn, cod, principal);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String editora, String livro, String isbn, String cod, Cad_Livro principal) {
		frmCadastroDeLivros = new JFrame();
		frmCadastroDeLivros.setTitle("Cadastro de Livros | Editar");
		frmCadastroDeLivros.setBounds(100, 100, 450, 300);
		//frmCadastroDeLivros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeLivros.getContentPane().setLayout(null);
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Edi\u00E7\u00E3o de Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 414, 136);
		frmCadastroDeLivros.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Editora");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 26, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Livro");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 62, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ISBN");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 98, 46, 14);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(73, 23, 306, 20);
		panel.add(textField);
		textField.setColumns(10);
		textField.setText(editora);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Campo "Editora" ganhou o foco
            	focusedTextField = textField;
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Campo "Editora" perdeu o foco
            }
        });
		
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(73, 59, 306, 20);
		panel.add(textField_1);
		textField_1.setText(livro);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(73, 95, 306, 20);
		panel.add(textField_2);
		textField_2.setText(isbn);
		
		
		// Criação do DocumentFilter para aceitar apenas números
        ((AbstractDocument) textField_2.getDocument()).setDocumentFilter(new DocumentFilter() {
            
            public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws BadLocationException {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < string.length(); i++) {
                    if (Character.isDigit(string.charAt(i))) {
                        sb.append(string.charAt(i));
                    }
                }
                super.insertString(fb, offset, sb.toString(), attr);
            }

            
            public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
                if (text == null) return;

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < text.length(); i++) {
                    if (Character.isDigit(text.charAt(i))) {
                        sb.append(text.charAt(i));
                    }
                }
                super.replace(fb, offset, length, sb.toString(), attrs);
            }
        });
		
		
		Button button = new Button("Concluir");
		button.setBounds(354, 229, 70, 22);
		frmCadastroDeLivros.getContentPane().add(button);
		button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	try {
		    		LivrosDAO edicao = new LivrosDAO();
					boolean vr = edicao.editLivro(textField.getText(),textField_1.getText(), textField_2.getText(), cod );
					if(vr) {
						JOptionPane.showMessageDialog(button, "Registro Alterado com sucesso!.", "Operação Concluida", JOptionPane.INFORMATION_MESSAGE);
						
						showTela(false);
						principal.showTable();
					}else {System.out.println("Falhou");}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		
		
		Button button_1 = new Button("Limpar Todos");
		button_1.setBounds(10, 229, 82, 22);
		frmCadastroDeLivros.getContentPane().add(button_1);
		button_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	textField.setText("");
		    	textField_1.setText("");
		    	textField_2.setText("");
		    }
		});
		
		Button button_2 = new Button("Limpar");
		button_2.setBounds(98, 229, 70, 22);
		frmCadastroDeLivros.getContentPane().add(button_2);
		button_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (focusedTextField != null) {
		            focusedTextField.setText(""); // Limpar o campo com foco
		        }
		    }
		});

		
		
		Button button_3 = new Button("Cancelar");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCadastroDeLivros.dispose();
			}
		});
		button_3.setBounds(266, 229, 70, 22);
		frmCadastroDeLivros.getContentPane().add(button_3);
	}
	
	public void showTela(boolean op) {
		frmCadastroDeLivros.setVisible(op);
	}
	
	private void setTexts(String ed, String tl, String isbn){}
}
