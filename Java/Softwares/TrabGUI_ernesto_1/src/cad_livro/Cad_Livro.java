package cad_livro;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import cad_produtos.ColorTable;
import connect.Conexao;

import javax.swing.border.EtchedBorder;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;

public class Cad_Livro {

	private JFrame frmCadastroDeLivros;
	private JTextField editoraField;
	private JTextField livroField;
	private JTextField isbnField;
	private JTable table;
	JPanel listaLivrosPanel;
	JPanel addLivroPanel;
	JPanel botoesPanel;
	JScrollPane scrollPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cad_Livro window = new Cad_Livro();
					window.frmCadastroDeLivros.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cad_Livro() {
		initialize();
		showTable();
		teste();
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastroDeLivros = new JFrame();
		frmCadastroDeLivros.setTitle("Cadastro de Livros");
		frmCadastroDeLivros.setBounds(100, 100, 498, 418);
		frmCadastroDeLivros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeLivros.getContentPane().setLayout(null);
		
		addLivroPanel = new JPanel();
		addLivroPanel.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Adicionar Livros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Adicionar Livros", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		addLivroPanel.setBounds(10, 0, 462, 104);
		frmCadastroDeLivros.getContentPane().add(addLivroPanel);
		addLivroPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Editora");
		lblNewLabel.setFont(new Font("Noto Sans Cond", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 25, 46, 14);
		addLivroPanel.add(lblNewLabel);
		
		JLabel lblLivro = new JLabel("Livro");
		lblLivro.setFont(new Font("Noto Sans Cond", Font.BOLD, 14));
		lblLivro.setBounds(10, 50, 46, 14);
		addLivroPanel.add(lblLivro);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Noto Sans Cond", Font.BOLD, 14));
		lblIsbn.setBounds(10, 75, 46, 14);
		addLivroPanel.add(lblIsbn);
		
		editoraField = new JTextField();
		editoraField.setBounds(63, 23, 327, 18);
		addLivroPanel.add(editoraField);
		editoraField.setColumns(10);
		
		livroField = new JTextField();
		livroField.setColumns(10);
		livroField.setBounds(63, 50, 327, 18);
		addLivroPanel.add(livroField);
		
		isbnField = new JTextField();
		isbnField.setColumns(10);
		isbnField.setBounds(63, 75, 129, 18);
		addLivroPanel.add(isbnField);
		
		// Criação do DocumentFilter para aceitar apenas números
        ((AbstractDocument) isbnField.getDocument()).setDocumentFilter(new DocumentFilter() {
            
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
		
		
		
		
		
		
		/* --- BOTOES --- */
		botoesPanel = new JPanel();
		botoesPanel.setBounds(10, 107, 462, 46);
		frmCadastroDeLivros.getContentPane().add(botoesPanel);
		botoesPanel.setLayout(null);
		
	    ImageIcon icon1 = new ImageIcon(getClass().getResource("/icons/inserir.png"));
	    ImageIcon icon2 = new ImageIcon(getClass().getResource("/icons/limpar.png"));
	    ImageIcon icon3 = new ImageIcon(getClass().getResource("/icons/salvar.png"));
	    ImageIcon icon4 = new ImageIcon(getClass().getResource("/icons/editar.png"));
	    ImageIcon icon5 = new ImageIcon(getClass().getResource("/icons/deletar.png"));
		
	    ImageIcon scaledIcon1 = scaleIcon(icon1, 26, 26); // Change 32, 32 to the desired width and height
	    JButton btn_addLivro = new JButton("", scaledIcon1);
	    btn_addLivro.setToolTipText("Adicionar Livro");
	    btn_addLivro.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String editora = editoraField.getText();
				String livro = livroField.getText();
				String isbn = isbnField.getText();
				
				if (editora.isEmpty() || livro.isEmpty() || isbn.isEmpty())  {
				    // Pelo menos um dos campos está vazio
				    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Campos vazios", JOptionPane.WARNING_MESSAGE);
				}else {
					LivrosDAO novoLivro = new LivrosDAO();
					try {
						boolean vr = novoLivro.insertLivro(editora, livro, isbn);
						if (vr) {
							JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso", "Operação Bem Sucedida!", JOptionPane.INFORMATION_MESSAGE);
							showTable();
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
	    	}
	    });
		btn_addLivro.setBounds(52, 7, 59, 30);
		botoesPanel.add(btn_addLivro);
		
		
		
		ImageIcon scaledIcon2 = scaleIcon(icon2, 26, 26);
		JButton btn_limparCampos = new JButton("", scaledIcon2);
		btn_limparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editoraField.setText("");
				isbnField.setText("");
				livroField.setText("");
			}
		});
		btn_limparCampos.setBounds(128, 7, 59, 30);
		botoesPanel.add(btn_limparCampos);
		
		
		ImageIcon scaledIcon3 = scaleIcon(icon3, 26, 26);
		JButton btn_salvar = new JButton("", scaledIcon3 );
		btn_salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showTable();
			}
		});
		btn_salvar.setBounds(209, 7, 59, 30);
		botoesPanel.add(btn_salvar);
		
		
		ImageIcon scaledIcon4 = scaleIcon(icon4, 26, 26);
		JButton btn_editar = new JButton("", scaledIcon4);
		btn_editar.setToolTipText("Editar dados Cadastrados");
		btn_editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				Cad_Livro window = new Cad_Livro();
				if (selectedRow != -1) {
					String codigo = table.getValueAt(selectedRow, 0).toString(); // Assumindo que o código está na primeira coluna
			        String editora = table.getValueAt(selectedRow, 1).toString();
			        String livro = table.getValueAt(selectedRow, 2).toString();
			        String isbn = table.getValueAt(selectedRow, 3).toString();
					
			        System.out.println(""+codigo+" "+editora+" "+livro+" "+isbn);
			        Editar edicao = new Editar(editora, livro, isbn, codigo, window);
					edicao.showTela(true);
					
				}else {JOptionPane.showMessageDialog(btn_editar, "Selecione uma linha antes de editar.", "Aviso", JOptionPane.WARNING_MESSAGE);}
			
				System.out.println("nada");
			}
		});
		btn_editar.setBounds(289, 7, 59, 30);
		botoesPanel.add(btn_editar);
		
		
		ImageIcon scaledIcon5 = scaleIcon(icon5, 26, 26);
		JButton btn_deletar = new JButton("", scaledIcon5);
		btn_deletar.setToolTipText("Apagar permanentemente um registro.");
		btn_deletar.setBounds(372, 7, 59, 30);
		botoesPanel.add(btn_deletar);
		
		btn_deletar.addActionListener(e -> {
		    // Verificar se há alguma linha selecionada na tabela
		    int selectedRow = table.getSelectedRow();
		    if (selectedRow != -1) {
		        // Obter os valores da linha selecionada
		    	String codigo = table.getValueAt(selectedRow, 0).toString(); 
		        // Chamar o método para deletar o registro no banco de dados
			    try {
			        LivrosDAO editar = new LivrosDAO();

			        boolean deletado = editar.delRegistro(codigo);

			        if (deletado) {
			            System.out.println("Registro deletado com sucesso!");
			        } else {
			            System.out.println("Falha ao deletar o registro.");
			        }
			    } catch (SQLException el) {
			        el.printStackTrace();
			    }

		        // Atualizar a exibição da tabela após a exclusão
		        showTable();
		    }
		});

		
		
		
		/* ----- Painel ----- */
		listaLivrosPanel = new JPanel();
		listaLivrosPanel.setBorder(new TitledBorder(null, "Lista de Livros", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		listaLivrosPanel.setBounds(10, 155, 462, 213);
		frmCadastroDeLivros.getContentPane().add(listaLivrosPanel);
		listaLivrosPanel.setLayout(null);
		
		
		/* ----- TABELA ----- */
        table = new JTable();
        table.setDefaultEditor(Object.class, null);          // Desabilitar eventos de edição ao clicar duas vezes
        table.getTableHeader().setReorderingAllowed(false);
        
        
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 25, 431, 177);
		listaLivrosPanel.add(scrollPane);
		
		table.getSelectionModel().addListSelectionListener(e -> {
		    // Verifique se alguma linha foi selecionada
		    if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
		        // Obtenha os valores da linha selecionada
		        int row = table.getSelectedRow();
		        String editora = table.getValueAt(row, 0).toString();
		        String livro = table.getValueAt(row, 1).toString();
		        String isbn = table.getValueAt(row, 2).toString();

		        // Faça o que for necessário com os valores obtidos
		        //System.out.println("Linha selecionada: Editora: " + editora + ", Livro: " + livro + ", ISBN: " + isbn);
		    }
		});
		
		addComponentListener();
	
	}
	
	
	public ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
	    Image image = icon.getImage();
	    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    return new ImageIcon(scaledImage);
	}
	
	public void showTable() {
		try {
            Connection conexao = new Conexao("nada").getConnection();
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM livros_cadastrados");

            // Armazenar os dados do ResultSet em uma lista
            List<Object[]> data = new ArrayList<>();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = resultSet.getObject(i + 1);
                }
                data.add(row);
            }

            // Configurar o modelo de tabela com os dados da lista
            DefaultTableModel tableModel = new DefaultTableModel();
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metaData.getColumnName(i));
            }
            for (Object[] row : data) {
                tableModel.addRow(row);
            }

            // Configurar o modelo de tabela no componente JTable
            table.setModel(tableModel);
            table.setDefaultRenderer(Object.class, new ColorTable());
            // Fechar a conexão, o statement e o resultSet
            resultSet.close();
            statement.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	private void addComponentListener() {
		frmCadastroDeLivros.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });
    }
	
    private void resizeComponents() {
        int frameWidth = frmCadastroDeLivros.getContentPane().getWidth();
        int frameHeight = frmCadastroDeLivros.getContentPane().getHeight();

        int pWidth = frameWidth -28; 
        int pHeight = frameHeight -280; 
        
        int tWidth = frameWidth -50;
        int tHeight = frameHeight - 170;
        
        addLivroPanel.setBounds(10, 0, pWidth, 104);
        botoesPanel.setBounds(10, 107, pWidth, 46);
        
        listaLivrosPanel.setBounds(10, 155, pWidth, tHeight);
        scrollPane.setBounds(10, 25, tWidth+5, tHeight-30);
        table.setBounds(0, 0, pWidth, pHeight);
        
    }


	


	public void teste() {
	    try {
	        LivrosDAO editar = new LivrosDAO();
	        
	        // Suponhamos que você queira atualizar o registro com os seguintes valores
	        String editora = "Nova qwe";
	        String titulo = "Novo ";
	        String isbn = "1234567890";
	        String codigo = "10"; // Suponha que o código do registro que você deseja atualizar seja 1

	        boolean atualizado = editar.editLivro(editora, titulo, isbn, codigo);

	        if (atualizado) {
	            System.out.println("Registro atualizado com sucesso!");
	        } else {
	            System.out.println("Falha ao atualizar o registro.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void teste_2() {
	    try {
	        LivrosDAO editar = new LivrosDAO();

	        // Suponhamos que você queira deletar o registro com o seguinte valor de código
	        String codigo = "1"; // Suponha que o código do registro que você deseja deletar seja 1

	        boolean deletado = editar.delRegistro(codigo);

	        if (deletado) {
	            System.out.println("Registro deletado com sucesso!");
	        } else {
	            System.out.println("Falha ao deletar o registro.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}
