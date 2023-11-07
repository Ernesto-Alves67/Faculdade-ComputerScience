package crud_Java;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import connect.Conexao;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HomeCrud {

    JFrame frmCrudHome;
    private JTable table;
    private JButton btnDeletar;
    private JButton btnInserir;
    private JButton btnAtualizar;
    private String username;
    JScrollPane scrollPane;
    JMenuBar menuBar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HomeCrud window = new HomeCrud();
                    window.frmCrudHome.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * @wbp.parser.entryPoint
     */
    public HomeCrud() {}
    public HomeCrud(String username) {
    	this.username = username;
        initialize();
        
        showTable();
    }

    private void initialize() {
        frmCrudHome = new JFrame();
        frmCrudHome.setTitle("Crud | Home");
        frmCrudHome.setBounds(100, 100, 700, 350);
        frmCrudHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmCrudHome.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Seja Bem-Vindo ");
        lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        lblNewLabel.setBounds(10, 33, 138, 20);
        frmCrudHome.getContentPane().add(lblNewLabel);

        JLabel NomeUser = new JLabel("");
        NomeUser.setFont(new Font("Verdana", Font.BOLD, 15));
        NomeUser.setBounds(150, 33, 129, 20);
        NomeUser.setText(username+"!");
        frmCrudHome.getContentPane().add(NomeUser);
        
        
        menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 434, 23);
        frmCrudHome.getContentPane().add(menuBar);

        JMenu mnNewMenu_1 = new JMenu("Conta");
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmNewMenuItem = new JMenuItem("Alterar login");
        mnNewMenu_1.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Alterar senha");
        mnNewMenu_1.add(mntmNewMenuItem_3);

        JMenuItem mntmNewMenuItem_4 = new JMenuItem("Permissões...");
        mnNewMenu_1.add(mntmNewMenuItem_4);

        JMenu mnNewMenu_2 = new JMenu("Editar");
        menuBar.add(mnNewMenu_2);

        JMenuItem mntmNewMenuItem_6 = new JMenuItem("Deletar Conta Usuário");
        mnNewMenu_2.add(mntmNewMenuItem_6);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("Add... Usuário");
        mnNewMenu_2.add(mntmNewMenuItem_5);

        JMenu mnNewMenu = new JMenu("opções");
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Sair");
        mnNewMenu.add(mntmNewMenuItem_2);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Ver versão");
        mnNewMenu.add(mntmNewMenuItem_1);
        
        
        /* ----- TABELA ----- */
        table = new JTable();
        table.setDefaultEditor(Object.class, null);          // Desabilitar eventos de edição ao clicar duas vezes
        table.getTableHeader().setReorderingAllowed(false);  // Desabilitar evento de mudança de posição de coluna 

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(12, 71, 593, 146);
        frmCrudHome.getContentPane().add(scrollPane);

        ListSelectionModel selectionModel = table.getSelectionModel(); // Crie um ouvinte de seleção de linhas
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                // O código aqui será executado quando ocorrer uma seleção de linha na tabela
                // Você pode acessar os dados da linha selecionada usando os métodos da tabela
                int selectedRow = table.getSelectedRow();
                // Faça algo com os dados da linha selecionada
            }
        });

        
        
        
        /* ----- BOTOES ----- */
        btnDeletar = new JButton("Deletar");
        btnDeletar.setBounds(10, 227, 138, 23);
        frmCrudHome.getContentPane().add(btnDeletar);

        btnInserir = new JButton("Inserir");
        btnInserir.setBounds(158, 227, 138, 23);
        frmCrudHome.getContentPane().add(btnInserir);

        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(306, 227, 138, 23);
        frmCrudHome.getContentPane().add(btnAtualizar);
        
        btnDeletar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// Crie um ouvinte de seleção de linhas
            	ListSelectionModel selectionModel = table.getSelectionModel();
            	selectionModel.addListSelectionListener(new ListSelectionListener() {
            	    public void valueChanged(ListSelectionEvent event) {
            	        // O código aqui será executado quando ocorrer uma seleção de linha na tabela
            	        if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
            	            int selectedRow = table.getSelectedRow();
            	            
            	            // Obter o valor atual de uma célula específica na linha selecionada
            	            //Object value = table.getValueAt(selectedRow, column);
            	            
            	            // Faça algo com o valor da célula, como exibir em um JOptionPane ou modificá-lo
            	            
            	            // Atualizar o valor da célula na linha selecionada
            	            //table.setValueAt(newValue, selectedRow, column);
            	        }  
            	    }
            	    
            	
            });}
           });

        btnInserir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código a ser executado quando o botão "Inserir" for clicado
                // Coloque seu código aqui
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código a ser executado quando o botão "Atualizar" for clicado
                // Coloque seu código aqui
            }
        });

        
        
        /* ----- REDIMENCIONAMENTO DE COMPONENTES -----*/
        addComponentListener();
    }

    private void addComponentListener() {
        frmCrudHome.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });
    }

    private void resizeComponents() {
        int frameWidth = frmCrudHome.getContentPane().getWidth();
        int frameHeight = frmCrudHome.getContentPane().getHeight();

        int tableWidth = frameWidth - 30; // Margem de 10 em cada lado
        int tableHeight = frameHeight - 150; // Ajuste de acordo com o posicionamento dos componentes

        scrollPane.setBounds(10, 71, tableWidth, tableHeight-20);
        table.setBounds(10, 71, tableWidth, tableHeight);
        btnDeletar.setBounds(10, frameHeight - 99, 138, 23);
        btnInserir.setBounds(158, frameHeight - 99, 138, 23);
        btnAtualizar.setBounds(306, frameHeight - 99, 138, 23);
        menuBar.setBounds(0, 0, frameWidth, 23);
        
    }
    	
    private void showTable() {
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
