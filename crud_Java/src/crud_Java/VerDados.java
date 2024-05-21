package crud_Java;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connect.Conexao;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VerDados {

    public JFrame frmDadosCadastrados;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VerDados window = new VerDados();
                    window.frmDadosCadastrados.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VerDados() {
        initialize();
        showTableData();
    }

    private void initialize() {
        frmDadosCadastrados = new JFrame();
        frmDadosCadastrados.setTitle("Crud | Dados Cadastrados");
        frmDadosCadastrados.setBounds(100, 100, 550, 300);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmDadosCadastrados.getContentPane().setLayout(null);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 58, 514, 192);
        frmDadosCadastrados.getContentPane().add(scrollPane);
        
        JLabel lblNewLabel = new JLabel("Informações Usuários:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(10, 31, 224, 25);
        frmDadosCadastrados.getContentPane().add(lblNewLabel);
        
        JButton btnNewButton = new JButton("Voltar...");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frmDadosCadastrados.setVisible(false);
        	}
        });
        btnNewButton.setBounds(0, 0, 89, 23);
        frmDadosCadastrados.getContentPane().add(btnNewButton);
    }

    private void showTableData() {
        try {
            Connection conexao = new Conexao().getConnection();
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users_1");

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
}