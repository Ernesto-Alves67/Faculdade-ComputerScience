package cad_produtos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import connect.Conexao;

import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

public class Cad_Produtos {

	private JFrame frmCadastroDeProdutos;
	private JTextField textField_prVenda;
	private JTextField textField_qtd;
	private JTextField textField_prCompra;
	private JTextField textField_nome;
	private JTable table;
	JScrollPane scrollPane;
	JMenuBar menuBar;
	private JButton btnSair;
	private JButton btnNewButton;
	private JButton btnAlterar;
	private JButton btnLimpar;
	JPanel panel_2;
	JPanel panelBotoes;
	JPanel panel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cad_Produtos window = new Cad_Produtos();
					window.frmCadastroDeProdutos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cad_Produtos() {
		initialize();
		showTable();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmCadastroDeProdutos = new JFrame();
		frmCadastroDeProdutos.setTitle("Cadastro de Produtos");
		frmCadastroDeProdutos.setBounds(100, 100, 726, 451);
		frmCadastroDeProdutos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeProdutos.getContentPane().setLayout(null);
		
		panelBotoes = new JPanel();
		panelBotoes.setBackground(SystemColor.inactiveCaption);
		panelBotoes.setBounds(10, 341, 687, 50);
		frmCadastroDeProdutos.getContentPane().add(panelBotoes);
		panelBotoes.setLayout(null);
		
		btnNewButton = new JButton("Salvar");
		btnNewButton.setBounds(10, 7, 89, 39);
		panelBotoes.add(btnNewButton);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(163, 7, 89, 39);
		panelBotoes.add(btnLimpar);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(344, 7, 89, 39);
		panelBotoes.add(btnAlterar);
		
		btnSair = new JButton("Sair");
		btnSair.setBounds(532, 7, 89, 39);
		panelBotoes.add(btnSair);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 40, 687, 164);
		frmCadastroDeProdutos.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
        /* ----- TABELA ----- */
        table = new JTable();
        
        table.setDefaultEditor(Object.class, null);          // Desabilitar eventos de edição ao clicar duas vezes
        table.getTableHeader().setReorderingAllowed(false);
        
        
        
		//JScrollPane 
        scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 687, 164);
		panel_1.add(scrollPane);
		showTable();
		
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(198, 208, 236));
		panel_2.setBounds(10, 215, 687, 115);
		frmCadastroDeProdutos.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Qtd:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(73, 9, 28, 14);
		panel_2.add(lblNewLabel);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNome.setBounds(65, 33, 40, 14);
		panel_2.add(lblNome);
		
		JLabel lblPreoVenda = new JLabel("Preço de Venda:");
		lblPreoVenda.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPreoVenda.setBounds(14, 58, 104, 14);
		panel_2.add(lblPreoVenda);
		
		JLabel lblPreoDeCompra = new JLabel("Preço de Compra:");
		lblPreoDeCompra.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPreoDeCompra.setBounds(10, 83, 95, 14);
		panel_2.add(lblPreoDeCompra);
		
		textField_prVenda = new JTextField();
		textField_prVenda.setBounds(110, 58, 86, 17);
		panel_2.add(textField_prVenda);
		textField_prVenda.setColumns(10);
		
		textField_qtd = new JTextField();
		textField_qtd.setColumns(10);
		textField_qtd.setBounds(110, 9, 86, 17);
		panel_2.add(textField_qtd);
		
		textField_prCompra = new JTextField();
		textField_prCompra.setColumns(10);
		textField_prCompra.setBounds(110, 81, 86, 17);
		panel_2.add(textField_prCompra);
		
		textField_nome = new JTextField();
		textField_nome.setColumns(10);
		textField_nome.setBounds(110, 34, 86, 17);
		panel_2.add(textField_nome);
		
		JLabel lblNewLabel_1 = new JLabel("Categoria:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(399, 9, 60, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Fornecedor:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(392, 38, 60, 14);
		panel_2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Status:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2.setBounds(415, 65, 40, 14);
		panel_2.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Modelo/Marca:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_3.setBounds(380, 90, 76, 14);
		panel_2.add(lblNewLabel_1_3);
		
		JComboBox cb_categoria = new JComboBox();
		cb_categoria.setBounds(469, 11, 115, 16);
		panel_2.add(cb_categoria);
		
		JComboBox cb_marcaModelo = new JComboBox();
		cb_marcaModelo.setBounds(469, 86, 115, 16);
		panel_2.add(cb_marcaModelo);
		
		JComboBox cb_Status = new JComboBox();
		cb_Status.setBounds(469, 64, 60, 16);
		panel_2.add(cb_Status);
		
		JComboBox cb_fornecedor = new JComboBox();
		cb_fornecedor.setBounds(469, 38, 115, 16);
		panel_2.add(cb_fornecedor);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 710, 29);
		frmCadastroDeProdutos.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Clientes");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("Produtos");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Vendas");
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("Fornecedores");
		menuBar.add(mnNewMenu_3);
		
		JMenu mnNewMenu_4 = new JMenu("Relatórios");
		menuBar.add(mnNewMenu_4);
		
		JMenu mnNewMenu_5 = new JMenu("Ordem de Serviço");
		menuBar.add(mnNewMenu_5);
		
		JMenu mnNewMenu_6 = new JMenu("Pesquisar");
		menuBar.add(mnNewMenu_6);
		
		JMenu mnNewMenu_7 = new JMenu("Sobre?");
		menuBar.add(mnNewMenu_7);
		
		/*frmCadastroDeProdutos.addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentResized(ComponentEvent e) {
		        if (frmCadastroDeProdutos.getExtendedState() == JFrame.MAXIMIZED_BOTH) {
		            System.out.println("Window maximized!");
		            resizeComponents();
		        }
		    }
		});
		resizeComponents();*/
		addComponentListener();
	}

	private void showTable() {
		try {
            Connection conexao = new Conexao("produtos_cadastrados").getConnection();
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM produtos");

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
            

            // Fechar a conexão, o statement e o resultSet
            resultSet.close();
            statement.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		
	}
	
    private void addComponentListener() {
    	frmCadastroDeProdutos.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });
    }
	
    private void resizeComponents() {
        int frameWidth = frmCadastroDeProdutos.getContentPane().getWidth();
        int frameHeight = frmCadastroDeProdutos.getContentPane().getHeight();

        int tableWidth = frameWidth -28; // Margem de 10 em cada lado
        int tableHeight = frameHeight -80; // Ajuste de acordo com o posicionamento dos componentes

        panel_1.setBounds(10, 30, tableWidth+10, tableHeight-200);
        scrollPane.setBounds(0, 0, tableWidth+10, tableHeight-200);
        table.setBounds(0, 0, tableWidth, tableHeight);
        btnSair.setBounds(532, 7, 89, 39);
        btnAlterar.setBounds(344, 7, 89, 39);
       
        btnLimpar.setBounds(163, 7, 89, 39);
        btnNewButton.setBounds(10, 7, 89, 39);
        menuBar.setBounds(0, 0, frameWidth, 23);
        
        panel_2.setBounds(10, frameHeight-237, frameWidth-20, 115);
        panelBotoes.setBounds(10, frameHeight-100, frameWidth-20, 50);
        
    }
}
