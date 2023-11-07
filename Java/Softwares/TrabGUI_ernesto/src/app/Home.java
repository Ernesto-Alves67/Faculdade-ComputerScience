package app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.KeyboardFocusManager;

import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;

import conexao.Conexao;
import conexao.Imoveis_DAO;

import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.text.NumberFormatter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

public class Home {

	private JFrame frmGestorImoveis;
	private JPanel dinamicView;
	private JTextField textField;
	private JTextField aTipoAcordo;
	private JTextField aClienteField;
	private JTextField id_imovel;
	private JTextField textField_5;
	private JTable table;
	private JTable table2;
	private JTextField nomeField;
	private JTextField cepField;
	private JTextField enderecoField;
	private JTextField telefoneField;
	private JTextField tpiField;
	private JTextField areaField;
	private JTextField anocField;
	private JTextField situacaoField;
	private JTextField zonaField;
	private JTextField valorField;
	private JTextField clienteField;
	private JTextField cpfField;
	private int id_cliente;
	private String id_proprietario;
	private String nome_usuario;
	private JTextField durAcordoField;
	private JTextField aValorField;
	private JTextField num_field;
	JPanel cadClientes;
	JPanel panel_cadastro;
	private String nome_mediador;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
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
	public Home() {
		initialize();
	}
	
	public Home(String nome_usuario) {
		nome_mediador = nome_usuario;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestorImoveis = new JFrame();
		frmGestorImoveis.getContentPane().setBackground(new Color(204, 0, 51));
		frmGestorImoveis.setTitle("Gestor Imoveis | Home");
		frmGestorImoveis.setBounds(100, 100, 661, 430);
		//frmGestorImoveis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		id_cliente = 0;
		/* ----- TABELAs ----- */
        table = new JTable();
        table2 = new JTable();
        table.setDefaultEditor(Object.class, null);          // Desabilitar eventos de edição ao clicar duas vezes
        table.getTableHeader().setReorderingAllowed(false);
        
        /* ----- T2----- */
        
        dinamicView = new JPanel(); 
		dinamicView = createCadastroPanel(frmGestorImoveis);
		
		JMenuBar menuBar = new JMenuBar();
		frmGestorImoveis.setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Acordos");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dinamicView = createAcordosPanel(frmGestorImoveis);
				resetMenuItemsBackground(menuBar,mntmNewMenuItem_1);
			}
		});
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Clientes");
		mntmNewMenuItem.setBackground(new Color(255, 255, 255));
		menuBar.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dinamicView = createCadastroPanel(frmGestorImoveis);
				
				
				resetMenuItemsBackground(menuBar,mntmNewMenuItem);
			}
		});
		menuBar.add(mntmNewMenuItem_1);
		
		JMenuItem consultaMenu = new JMenuItem("Consultas");
		consultaMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dinamicView = createConsultasPanel(frmGestorImoveis);
				
				resetMenuItemsBackground(menuBar,consultaMenu);
			}
		});
		menuBar.add(consultaMenu);
		
		JMenuItem sairMenu = new JMenuItem("Sair");
		sairMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int escolha = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Finalizar Seção", JOptionPane.YES_NO_OPTION);

			        if (escolha == JOptionPane.YES_OPTION) {
			            setVisibilidade(false);
			            Gestor_Imoveis.setVisibilidade(true);
			        }
			}
		});
		menuBar.add(sairMenu);
		frmGestorImoveis.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mediador: ");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 0, 65, 14);
		frmGestorImoveis.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setBounds(84, 0, 120, 14);
		lblNewLabel_8.setText(nome_mediador);
		frmGestorImoveis.getContentPane().add(lblNewLabel_8);
		
		
	}

	public void setVisibilidade(boolean b) {
		frmGestorImoveis.setVisible(b);
		
	}
	
	
	public JPanel createAcordosPanel(JFrame view) {
	        view.getContentPane().remove(dinamicView);
	        view.repaint();
	    	JPanel panel = new JPanel();
	    	panel.setBackground(new Color(204, 0, 51));
			panel.setBounds(10, 11, 625, 341);
			frmGestorImoveis.getContentPane().add(panel);
			panel.setLayout(null);
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBackground(new Color(255, 204, 102));
			tabbedPane.setBounds(10, 11, 605, 319);
			panel.add(tabbedPane);
			
			
			table2.setDefaultEditor(Object.class, null);          // Desabilitar eventos de edição ao clicar duas vezes
			table2.getTableHeader().setReorderingAllowed(false);
			
			
			
			
			JPanel negociarPanel = new JPanel();
			negociarPanel.setBackground(new Color(255, 204, 153));
			negociarPanel.setBorder(new TitledBorder(null, "Dispon\u00EDveis", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			tabbedPane.addTab("Negociar Imoveis", null, negociarPanel, null);
			negociarPanel.setLayout(null);
			
			JPanel panel_3 = new JPanel();
			panel_3.setBounds(10, 15, 560, 113);
			negociarPanel.add(panel_3);
			panel_3.setLayout(null);
			
			
			
			JScrollPane disponivelPane = new JScrollPane(table2);
			disponivelPane.setBounds(0, 0, 560, 113);
			panel_3.add(disponivelPane);
			
			table2.getSelectionModel().addListSelectionListener(e -> {
			    if (!e.getValueIsAdjusting()) { 
			        int selectedRow = table2.getSelectedRow();
			        
			        if (selectedRow != -1) { // 
			            // Capturar os campos da linha selecionada
			            Object id_im = table2.getValueAt(selectedRow, 0); 
			            Object situ = table2.getValueAt(selectedRow, 6);
			            Object valor = table2.getValueAt(selectedRow, 5);
			            Object id_prop = table2.getValueAt(selectedRow, 7);
			            // mediador = user_name
			            //
			            
			            id_proprietario = "" + id_prop;
			            id_imovel.setText("" + id_im);
			            			            
			            if (situ != null && situ.toString().equals("A venda")) {
			                aTipoAcordo.setText("Compra");
			                durAcordoField.setEnabled(false);
			            }
			            if (situ != null && situ.toString().equals("Alocavel")) {
			            	aTipoAcordo.setText("Aluguel");
			            }
			            
			            if(valor != null) {
			            	aValorField.setText("" + valor);
			            }
			        }
			        
			    }
			});
			showAvaliable("disponiveis");
			JLabel lblNewLabel_2 = new JLabel("Imóvel:");
			lblNewLabel_2.setBounds(10, 139, 70, 14);
			negociarPanel.add(lblNewLabel_2);
			
			JLabel lblaCliente = new JLabel("Cliente:");
			lblaCliente.setBounds(10, 167, 70, 14);
			negociarPanel.add(lblaCliente);
			
			JLabel lblaTipo = new JLabel("Transação:");
			lblaTipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblaTipo.setBounds(359, 139, 60, 14);
			negociarPanel.add(lblaTipo);
			
			JButton btnNewButton = new JButton("Concluir");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Imoveis_DAO acordo = new Imoveis_DAO();
					if(!id_proprietario.equals(""+id_cliente)) {
						try {
							boolean vr = false;
							vr = acordo.crAcordo(id_imovel.getText(), aTipoAcordo.getText(), id_proprietario, ""+id_cliente, nome_usuario, null );
							if(vr) {
								Imoveis_DAO.updateSituacao(id_imovel.getText());
								JOptionPane.showMessageDialog(null, "Transação Realizada com sucesso!", "Operação Sucedida.", JOptionPane.INFORMATION_MESSAGE);
								showAvaliable("disponiveis");
							}else {
								JOptionPane.showMessageDialog(null, "Falha ao tentar Acessar o banco de dados", "Falha na Operação", JOptionPane.INFORMATION_MESSAGE);
	
							}
						}catch(SQLException e1) {
							e1.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Não é permitido efetuar a transação do imovel para seu proprietario.", "Falha na Operação", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnNewButton.setBounds(481, 259, 89, 23);
			negociarPanel.add(btnNewButton);
			
			aTipoAcordo = new JTextField();
			aTipoAcordo.setBounds(429, 139, 141, 17);
			negociarPanel.add(aTipoAcordo);
			aTipoAcordo.setColumns(10);
			
			aClienteField = new JTextField();
			aClienteField.setColumns(10);
			aClienteField.setBounds(83, 164, 220, 17);
			negociarPanel.add(aClienteField);
			aClienteField.addKeyListener(new KeyListener() {
	            @Override
	            public void keyTyped(KeyEvent e) {
	            }

	            @Override
	            public void keyPressed(KeyEvent e) {
	                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                    String nome = aClienteField.getText();
	                    // Realizar a busca na base de dados usando o 'nome'
	                    int resultadoDaBusca = -1;
						try {
							resultadoDaBusca = Imoveis_DAO.realizarBusca(nome);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                    
	                    if(resultadoDaBusca > -1) {
							JOptionPane.showMessageDialog(null, "Cliente verificado!", "Operação Sucedida", JOptionPane.INFORMATION_MESSAGE);
							id_cliente = resultadoDaBusca;

	                    }else {
	                    	JOptionPane.showMessageDialog(null, "Cliente não existe em nossa base de dados. ", "Operação Falhou", JOptionPane.INFORMATION_MESSAGE);
	                    }
	                    
	                }
	            }

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}

	
	        });
			
			id_imovel = new JTextField();
			id_imovel.setColumns(10);
			id_imovel.setBounds(83, 139, 220, 17);
			negociarPanel.add(id_imovel);
			
			JLabel lblNewLabel_6 = new JLabel("Duração Acordo:");
			lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNewLabel_6.setBounds(330, 167, 89, 14);
			negociarPanel.add(lblNewLabel_6);
			
			durAcordoField = new JTextField();
			durAcordoField.setColumns(10);
			durAcordoField.setBounds(429, 164, 141, 17);
			negociarPanel.add(durAcordoField);
			
			JLabel lblNewLabel_7 = new JLabel("Valor:");
			lblNewLabel_7.setBounds(10, 195, 46, 14);
			negociarPanel.add(lblNewLabel_7);
			
			aValorField = new JTextField();
			aValorField.setColumns(10);
			aValorField.setBounds(83, 192, 150, 17);
			negociarPanel.add(aValorField);
			
			    
			
			JPanel buscarPanel = new JPanel();
			buscarPanel.setBackground(new Color(255, 204, 153));
			tabbedPane.addTab("Buscar Acordos", null, buscarPanel, null);
			buscarPanel.setLayout(null);
			
			JLabel lblImovel = new JLabel("Buscar:");
			lblImovel.setBounds(10, 12, 40, 18);
			lblImovel.setFont(new Font("Noto Sans Cond", Font.BOLD, 13));
			buscarPanel.add(lblImovel);
			
			textField = new JTextField();
			textField.setBounds(60, 13, 264, 18);
			textField.setColumns(10);
			buscarPanel.add(textField);
			
			JLabel lblAno = new JLabel("Filtrar por:");
			lblAno.setFont(new Font("Noto Sans Cond", Font.BOLD, 13));
			lblAno.setBounds(385, 12, 64, 18);
			buscarPanel.add(lblAno);
			
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(459, 11, 111, 22);
			buscarPanel.add(comboBox);
			
			tabbedPane.addChangeListener(new ChangeListener() {
			        @Override
			        public void stateChanged(ChangeEvent e) {
			            // Obtém o índice da aba selecionada
			            int selectedIndex = tabbedPane.getSelectedIndex();
			            //System.out.println("A aba selecionada tem índice: " + selectedIndex);
			            if (selectedIndex == 0) {
			            
			            	showAvaliable("disponiveis");
			            } else if (selectedIndex == 1) {
			            	
			            	showTable("acordos");
			            }
			        }
			    });
			
	    	JPanel panel_4 = new JPanel();
	    	panel_4.setBackground(new Color(255, 204, 153));
	    	panel_4.setBorder(new TitledBorder(null, "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    	panel_4.setBounds(0, 57, 600, 234);
	    	buscarPanel.add(panel_4);
	    	panel_4.setLayout(null);
	    	
	    	JScrollPane scrollPane_1 = new JScrollPane();
	    	scrollPane_1.setBounds(10, 13, 580, 212);
	    	panel_4.add(scrollPane_1);
	    	
	    	table = new JTable();
	    	
	    	scrollPane_1.setViewportView(table);
			
			
			
			
			
			
			view.revalidate();
	    	return panel;
	    }
	   
	   
	public void showTable(String op) {
	        try {
	            Connection conexao = new Conexao().getConnection();
	            Statement statement = conexao.createStatement();
	            ResultSet resultSet;
	            System.out.println(op);
	            if (op.equals("acordos")) {
	        		resultSet = statement.executeQuery("SELECT * FROM " + op + " ORDER BY id");
	        		//System.out.println("Entrou");
	            }
	            resultSet = statement.executeQuery("SELECT * FROM " + op);
	           
	            

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
	            if (op.equals("acordos")) {
	            	table.setModel(tableModel);
	            	table2.setModel(tableModel);
	            }else {table2.setModel(tableModel);
	            table.setModel(tableModel);}
	            
	            for (int i = 1; i <= columnCount; i++) {
	                tableModel.addColumn(metaData.getColumnName(i));
	            }
	            for (Object[] row : data) {
	                tableModel.addRow(row);
	            }
	            
	            // Fechar a conexão, o statement e o resultSet
	            resultSet.close();
	            statement.close();
	            conexao.close();
	        } catch (SQLException e) {
	        	System.out.println("Erro");
	            e.printStackTrace();
	        }
	    
	    }
	   
	private void showAvaliable(String op) {
	        try {
	            Connection conexao = new Conexao().getConnection();
	            Statement statement = conexao.createStatement();
	            ResultSet resultSet;
	            
	            if (op.equals("disponiveis")) {
	                 resultSet = statement.executeQuery("SELECT * FROM imoveis"+ " WHERE situacao = 'A venda'");
	            }
	            else  {resultSet = statement.executeQuery("SELECT * FROM " + op);}
	            
	            
	            

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
	            table2.setModel(tableModel);

	            // Fechar a conexão, o statement e o resultSet
	            resultSet.close();
	            statement.close();
	            conexao.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    
	    }
	   
	public JPanel createConsultasPanel(JFrame view) {
	    	
	    	view.getContentPane().remove(dinamicView);
	        view.repaint();
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 204, 153));
			panel.setBounds(10, 35, 625, 317);
			frmGestorImoveis.getContentPane().add(panel);
			panel.setLayout(null);
			
	        
	        
			JScrollPane scrollPane = new JScrollPane(table);
			
			scrollPane.setBounds(10, 65, 605, 241);
			panel.add(scrollPane);
			
			
			JLabel lblNewLabel = new JLabel("Selecionar Base:");
			lblNewLabel.setBounds(10, 13, 87, 14);
			lblNewLabel.setFont(new Font("Noto Sans Cond", Font.BOLD, 13));
			panel.add(lblNewLabel);
			
			JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.setBounds(107, 13, 199, 17);
	        comboBox.addItem("clientes");
	        comboBox.addItem("acordos");
	        comboBox.addItem("imoveis");
			panel.add(comboBox);
			
	        comboBox.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String selectedOption = (String) comboBox.getSelectedItem();
	      
	                showTable(selectedOption);

	            }
	        });
			
			JLabel lblbuscar = new JLabel("Buscar:");
			lblbuscar.setBounds(10, 38, 46, 14);
			lblbuscar.setFont(new Font("Noto Sans Cond", Font.BOLD, 13));
			panel.add(lblbuscar);
			
			textField_5 = new JTextField();
			textField_5.setBounds(56, 40, 250, 14);
			panel.add(textField_5);
			textField_5.setColumns(10);
			

			view.revalidate();
			showTable("clientes");
	        return panel;
	    } 

	
	private void resetMenuItemsBackground(JMenuBar menuBar, JMenuItem selectedItem) {
	    for (int i = 0; i < menuBar.getMenuCount(); i++) {
	        JMenu menu = menuBar.getMenu(i);
	        if (menu != null) {
	            for (int j = 0; j < menu.getItemCount(); j++) {
	                JMenuItem menuItem = menu.getItem(j);
	                if (menuItem != null) {
	                    if (menuItem == selectedItem) {
	                        Font originalFont = menuItem.getFont();
	                        Font boldFont = new Font(originalFont.getName(), Font.BOLD, originalFont.getSize());
	                        menuItem.setFont(boldFont);
	                    } else {
	                        menuItem.setFont(menu.getFont()); // Define o estilo de fonte padrão
	                    }
	                }
	            }
	        }
	    }
	}

	
	private JPanel createCadastroPanel(JFrame view) {
		view.getContentPane().remove(dinamicView);
        view.repaint();
    	
        panel_cadastro = new JPanel();
        panel_cadastro.setBackground(new Color(51, 204, 153));
        panel_cadastro.setBounds(10, 25, 625, 316);
		frmGestorImoveis.getContentPane().add(panel_cadastro);
		panel_cadastro.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 204, 153));
		tabbedPane.setBounds(0, 0, 625, 321);
		panel_cadastro.add(tabbedPane);
		tabbedPane.addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent e) {
	            // Obtém o índice da aba selecionada
	            int selectedIndex = tabbedPane.getSelectedIndex();
	           
	            //System.out.println("A aba selecionada tem índice: " + selectedIndex);
	            
	            if (selectedIndex == 0) {
	            
	            
	            } else if (selectedIndex == 1) {
	            	
	            }
	        }
	    });
		// -------------------------------------------------------------------------------cadImoveis
		JPanel cadImoveis = new JPanel();
		cadImoveis.setBackground(new Color(255, 204, 153));
		tabbedPane.addTab("Cadastrar Imoveis", null, cadImoveis, null);
		cadImoveis.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Tipo Imovel:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(10, 45, 60, 14);
		cadImoveis.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Area Contruída:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3_1.setBounds(10, 73, 77, 14);
		cadImoveis.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Ano Construção:");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3_2.setBounds(10, 115, 89, 14);
		cadImoveis.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("Situação:");
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3_3.setBounds(10, 162, 60, 14);
		cadImoveis.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_4 = new JLabel("Zona/Localidade:");
		lblNewLabel_3_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3_4.setBounds(10, 192, 89, 14);
		cadImoveis.add(lblNewLabel_3_4);
		
		JLabel lblNewLabel_3_5 = new JLabel("Valor:");
		lblNewLabel_3_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3_5.setBounds(10, 232, 60, 14);
		cadImoveis.add(lblNewLabel_3_5);
		
		tpiField = new JTextField();
		tpiField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			        manager.focusNextComponent();
			}
		});
		
		tpiField.setToolTipText("ex.: terreno, casa, apartamento, edificio.");
		tpiField.setBounds(108, 42, 167, 20);
		cadImoveis.add(tpiField);
		tpiField.setColumns(10);
		
		areaField = new JTextField();
		areaField.setColumns(10);
		areaField.setBounds(108, 70, 167, 20);
		cadImoveis.add(areaField);
		areaField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			        manager.focusNextComponent();
			}
		});
		
		/// Tratamento para permitir apenas numeros
		((AbstractDocument) areaField.getDocument()).setDocumentFilter(new DocumentFilter() {
	          
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


		anocField = new JTextField();
		anocField.setToolTipText("Ano de conclusão da construção.");
		anocField.setColumns(10);
		anocField.setBounds(109, 111, 167, 20);
		cadImoveis.add(anocField);
		((AbstractDocument) anocField.getDocument()).setDocumentFilter(new DocumentFilter() {
	          
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
		anocField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			        manager.focusNextComponent();
			}
		});
		
		situacaoField = new JTextField();
		situacaoField.setToolTipText("Ex.: Alugavel, A venda.");
		situacaoField.setColumns(10);
		situacaoField.setBounds(109, 156, 167, 20);
		cadImoveis.add(situacaoField);
		((AbstractDocument) situacaoField.getDocument()).setDocumentFilter(new DocumentFilter() {
	          
	           public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws BadLocationException {
	               StringBuilder sb = new StringBuilder();
	               for (int i = 0; i < string.length(); i++) {
	                   if (!Character.isDigit(string.charAt(i))) {
	                       sb.append(string.charAt(i));
	                   }
	               }
	               super.insertString(fb, offset, sb.toString(), attr);
	           }
	          
	           public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
	               if (text == null) return;
	               StringBuilder sb = new StringBuilder();
	               for (int i = 0; i < text.length(); i++) {
	                   if (!Character.isDigit(text.charAt(i))) {
	                       sb.append(text.charAt(i));
	                   }
	               }
	               super.replace(fb, offset, length, sb.toString(), attrs);
	           }
	       });
		situacaoField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			        manager.focusNextComponent();
			}
		});
		
		
		zonaField = new JTextField();
		zonaField.setToolTipText("Bairro em que se encontra a propriedade.");
		zonaField.setColumns(10);
		zonaField.setBounds(109, 189, 167, 20);
		cadImoveis.add(zonaField);
		zonaField.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Transfere o foco para o próximo componente (campo)
		        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		        manager.focusNextComponent();
		    }
		});
		
		valorField = new JTextField();
		valorField.setColumns(10);
		valorField.setBounds(108, 229, 167, 20);
		cadImoveis.add(valorField);
		((AbstractDocument) valorField.getDocument()).setDocumentFilter(new DocumentFilter() {
	          
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
		valorField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			        manager.focusNextComponent();
			}
		});
		
		JLabel lblNewLabel_3_6 = new JLabel("Cliente:");
		lblNewLabel_3_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_3_6.setBounds(10, 14, 60, 14);
		cadImoveis.add(lblNewLabel_3_6);
		
		clienteField = new JTextField();
		clienteField.setToolTipText("Digite o nome do dono do imovel");
		clienteField.setColumns(10);
		clienteField.setBounds(108, 11, 167, 20);
		cadImoveis.add(clienteField);
		clienteField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String nome = clienteField.getText();
                    // Realizar a busca na base de dados usando o 'nome'
                    int resultadoDaBusca = -1;
					try {
						resultadoDaBusca = Imoveis_DAO.realizarBusca(nome);
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
                    
                    if(resultadoDaBusca > -1) {
						JOptionPane.showMessageDialog(null, "Cliente verificado!", "Operação Sucedida", JOptionPane.INFORMATION_MESSAGE);
						id_cliente = resultadoDaBusca;
						KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
					    manager.focusNextComponent();

                    }else {
                    	JOptionPane.showMessageDialog(null, "Cliente não existe em nossa base de dados. ", "Operação Falhou", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
		
		
		
		JLabel lblNewLabel_4 = new JLabel("*Cliente deve possuir cadastro na base de dados.");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(281, 14, 268, 14);
		cadImoveis.add(lblNewLabel_4);
		
		JButton btncadImovel = new JButton("Cadastrar");
		btncadImovel.setBackground(new Color(255, 255, 255));
		btncadImovel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Imoveis_DAO cad = new Imoveis_DAO();
				
				String tipologia = tpiField.getText();
				String area = areaField.getText();
				String ano = anocField.getText();
				String zona = zonaField.getText();
				String valor = valorField.getText();
				String situ = situacaoField.getText();
				
				
				if (tipologia.isEmpty() || area.isEmpty() || ano.isEmpty() || zona.isEmpty() || valor.isEmpty() || situ.isEmpty())  {
				    // Pelo menos um dos campos está vazio
				    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Campos vazios", JOptionPane.WARNING_MESSAGE);
				}else {
				
				try {
					boolean vr = false;
					vr = cad.cadastrarImoveis(tpiField.getText(), areaField.getText(), anocField.getText(), zonaField.getText(), valorField.getText(), situacaoField.getText(), String.valueOf(id_cliente));
					if(vr) {
						JOptionPane.showMessageDialog(null, "Cadastro Criado com sucesso!", "Operação Sucedida.", JOptionPane.INFORMATION_MESSAGE);
						
					}else {
						JOptionPane.showMessageDialog(null, "Falha ao tentar Acessar o banco de dados", "Falha no Cadastro", JOptionPane.INFORMATION_MESSAGE);

					}
				}catch(SQLException el ) {
					el.printStackTrace();
				}				
			}}
		});
		btncadImovel.setBounds(381, 259, 89, 23);
		cadImoveis.add(btncadImovel);
		
		JLabel lblNewLabel_5 = new JLabel("R$");
		lblNewLabel_5.setBounds(88, 232, 22, 14);
		cadImoveis.add(lblNewLabel_5);
		
		
		// -----------------------------------------------------------------------------------cadClientes //
		cadClientes = new JPanel();
		cadClientes.setBackground(new Color(255, 204, 153));
		tabbedPane.addTab("Cadastrar Clientes", null, cadClientes, null);
		cadClientes.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNome.setBounds(10, 32, 46, 14);
		cadClientes.add(lblNome);
		
		JLabel lblcep = new JLabel("CEP:");
		lblcep.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblcep.setBounds(10, 74, 33, 14);
		cadClientes.add(lblcep);
		
		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEndereco.setBounds(215, 74, 54, 14);
		cadClientes.add(lblEndereco);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTelefone.setBounds(10, 117, 55, 14);
		cadClientes.add(lblTelefone);
		
		JLabel lbltipoCliente = new JLabel("Tipo Cliente:");
		lbltipoCliente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbltipoCliente.setBounds(340, 121, 77, 14);
		cadClientes.add(lbltipoCliente);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Vendedor", "Alocatario", "Alocador", "Comprador", "Misto"}));
		comboBox.setBounds(441, 117, 96, 22);
		cadClientes.add(comboBox);
		
		nomeField = new JTextField();
		nomeField.setBounds(48, 29, 181, 20);
		cadClientes.add(nomeField);
		nomeField.setColumns(10);
		nomeField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			        manager.focusNextComponent();
			}
		});
		
		cepField = new JTextField();
		cepField.setBounds(48, 71, 157, 20);
		cadClientes.add(cepField);
		cepField.setColumns(10);
		cepField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			        manager.focusNextComponent();
			}
		});
		((AbstractDocument) cepField.getDocument()).setDocumentFilter(new DocumentFilter() {
	          
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
		
		enderecoField = new JTextField();
		enderecoField.setColumns(10);
		enderecoField.setBounds(279, 74, 181, 20);
		cadClientes.add(enderecoField);
		enderecoField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			        manager.focusNextComponent();
			}
		});
		
		telefoneField = new JTextField();
		telefoneField.setColumns(10);
		telefoneField.setBounds(75, 114, 181, 20);
		cadClientes.add(telefoneField);
		telefoneField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			        manager.focusNextComponent();
			}
		});
		((AbstractDocument) telefoneField.getDocument()).setDocumentFilter(new DocumentFilter() {
	          
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
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Imoveis_DAO cad = new Imoveis_DAO();
				
				String nome = nomeField.getText();
				String endereco = enderecoField.getText();
				String cpf = cpfField.getText();
				String telefone = telefoneField.getText();
				String tipocliente = String.valueOf(comboBox.getSelectedItem());
				String numero = num_field.getText();
				if (nome.isEmpty() || endereco.isEmpty()|| cpf.isEmpty() || telefone.isEmpty() || tipocliente.isEmpty() || numero.isEmpty())  {
				    // Pelo menos um dos campos está vazio
				    JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Campos vazios", JOptionPane.WARNING_MESSAGE);
				}else {
				
				try {
					boolean vr = false;
					vr = cad.cadastrarClientes(nomeField.getText(), enderecoField.getText(), cpfField.getText(), telefoneField.getText(), String.valueOf(comboBox.getSelectedItem()) );
					if(vr) {
						JOptionPane.showMessageDialog(null, "Cadastro Criado com sucesso!", "Operação Sucedida.", JOptionPane.INFORMATION_MESSAGE);
						dinamicView = createCadastroPanel(frmGestorImoveis);
					}
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}}
		});
		btnCadastrar.setBounds(392, 259, 114, 23);
		valorField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               btncadImovel.doClick();
            }
        });
		cadClientes.add(btnCadastrar);
		((AbstractDocument) valorField.getDocument()).setDocumentFilter(new DocumentFilter() {
	          
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
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCpf.setBounds(280, 32, 46, 14);
		cadClientes.add(lblCpf);
		
		cpfField = new JTextField();
		cpfField.setColumns(10);
		cpfField.setBounds(315, 29, 181, 20);
		cadClientes.add(cpfField);
		
		JLabel lblnum = new JLabel("Número:");
		lblnum.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblnum.setBounds(491, 74, 46, 14);
		cadClientes.add(lblnum);
		
		num_field = new JTextField();
		num_field.setBounds(536, 71, 46, 20);
		cadClientes.add(num_field);
		num_field.setColumns(10);
		((AbstractDocument) num_field.getDocument()).setDocumentFilter(new DocumentFilter() {
	          
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
		
		cpfField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
			        manager.focusNextComponent();
			}
		});
		((AbstractDocument) cpfField.getDocument()).setDocumentFilter(new DocumentFilter() {
	          
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
		
		view.revalidate();
        return panel_cadastro;
	}
	
	private void addComponentListener() {
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
        
        cadClientes.setBounds(10, 0, pWidth, 104);
        //botoesPanel.setBounds(10, 107, pWidth, 46);
        panel_cadastro.setBounds(10, 0, pWidth, 104);
        //listaLivrosPanel.setBounds(10, 155, pWidth, tHeight);
        //scrollPane.setBounds(10, 25, tWidth+5, tHeight-30);
        table.setBounds(0, 0, pWidth, pHeight);
        
    }
}
