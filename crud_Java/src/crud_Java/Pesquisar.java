package crud_Java;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connect.Conexao;

import java.awt.Font;
import javax.swing.JScrollPane;





public class Pesquisar {

	public JFrame frmCrudPesquisar;
    private JTextField textFieldQuery;
    private JTable table;
    
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pesquisar window = new Pesquisar();
					window.frmCrudPesquisar.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Pesquisar() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
        frmCrudPesquisar = new JFrame();
        frmCrudPesquisar.setTitle("Crud | Pesquisar");
        frmCrudPesquisar.setBounds(100, 100, 450, 300);
        frmCrudPesquisar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmCrudPesquisar.getContentPane().setLayout(null);

        JLabel lblQuery = new JLabel("Buscar:");
        lblQuery.setBounds(10, 10, 43, 20);
        frmCrudPesquisar.getContentPane().add(lblQuery);

        textFieldQuery = new JTextField();
        textFieldQuery.setBounds(63, 10, 266, 20);
        frmCrudPesquisar.getContentPane().add(textFieldQuery);
        textFieldQuery.setColumns(10);
        textFieldQuery.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String query = textFieldQuery.getText();
        		showData(query);
        	}
        });

        JButton btnExecute = new JButton("Executar");
        btnExecute.setBounds(339, 10, 81, 20);
        frmCrudPesquisar.getContentPane().add(btnExecute);
        
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 71, 410, 179);
        frmCrudPesquisar.getContentPane().add(scrollPane);

        btnExecute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String loginName = textFieldQuery.getText();
                showData(loginName);
            }
        });
	}

	/*private void executeQuery(String query) {
	    try {
	        Connection conexao = new Conexao().getConnection();
	        PreparedStatement statement = conexao.prepareStatement("SELECT * FROM users_1 WHERE loginusuario LIKE ?");
	        statement.setString(1, "%" + query + "%");
	        ResultSet resultSet = statement.executeQuery();

	        // Limpar o campo de exibição dos resultados
	        SQL_Resultados.setText("");

	        // Exibir os resultados na área de texto
	        while (resultSet.next()) {
	            // Aqui você pode acessar os valores retornados pela consulta e exibi-los na área de texto
	            String resultado = resultSet.getString("loginusuario"); // Substitua "coluna" pelo nome real da coluna na sua tabela
	            SQL_Resultados.append(resultado + "\n");
	        }

	        // Fechar a conexão, o statement e o resultSet
	        resultSet.close();
	        statement.close();
	        conexao.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	*/
	private void showTableData(String query) {
	    try {
	        Connection conexao = new Conexao().getConnection();
	        PreparedStatement statement = conexao.prepareStatement("SELECT * FROM users_1 WHERE loginusuario LIKE ?");
	        statement.setString(1, "%" + query + "%");
	        ResultSet resultSet = statement.executeQuery();

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

	        // Configurar o modelo de tabela no componente JTable existente
	        table.setModel(tableModel);

	        // Fechar a conexão, o statement e o resultSet
	        resultSet.close();
	        statement.close();
	        conexao.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	private void showData(String query) {
	    try {
	        Connection conexao = new Conexao().getConnection();
	        PreparedStatement statement = conexao.prepareStatement("SELECT id_usuario, nome_usuario, login_usuario FROM users_1 WHERE login_usuario LIKE ?");
	        statement.setString(1, "%" + query + "%");
	        ResultSet resultSet = statement.executeQuery();

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

	        // Configurar o modelo de tabela com as quatro colunas desejadas
	        DefaultTableModel tableModel = new DefaultTableModel();
	        tableModel.addColumn("id_usuario");
	        tableModel.addColumn("nome_usuario");
	        tableModel.addColumn("login_usuario");
	      
	        for (Object[] row : data) {
	            Object[] rowData = { row[0], row[1], row[2]};
	            tableModel.addRow(rowData);
	        }

	        // Configurar o modelo de tabela no componente JTable existente
	        table.setModel(tableModel);

	        // Fechar a conexão, o statement e o resultSet
	        resultSet.close();
	        statement.close();
	        conexao.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}	
}	
