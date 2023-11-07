package reservas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class SRI {

	private JFrame frmReservaEventos;
	private JTable table;
	private DefaultTableModel tableModel;
	private JPanel dinamicPanel;
	private JTextField qnt_Ingressofield;
	private JTextField eventofield;
	private JTextField datafield;
	private JTextField horafield;
	private Object nomeEvento; 
	private Object dataEvento;
	private Object horaEvento;
	private Object n_ingressos;
	private Object preco;
	private JTextField cpfField;
	private JTextField textField_1;
	private JTextField textField_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SRI window = new SRI();
					window.frmReservaEventos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SRI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReservaEventos = new JFrame();
		frmReservaEventos.setTitle("Reserva Eventos");
		frmReservaEventos.setBounds(100, 100, 698, 419);
		frmReservaEventos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReservaEventos.getContentPane().setLayout(null);
		
		dinamicPanel = new JPanel();
		dinamicPanel = listaEventosPanel(frmReservaEventos);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 682, 22);
		frmReservaEventos.getContentPane().add(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Adicionar Eventos");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add_Eventos adEvento = new Add_Eventos(tableModel);
				adEvento.setVisibilidade(true);
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Selecionar Evento");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/// vreifica se evento foi selecionado na tabela
				// se sim, exibe paine de confirmação reserva
				if(table.getSelectedRow() != -1) {
					dinamicPanel = confirmaReservaPanel(frmReservaEventos);
				}else {
					JOptionPane.showMessageDialog(null, "Selecione um Evento abaixo", "Falha na Operação", JOptionPane.INFORMATION_MESSAGE);

				}
			}
		});
		menuBar.add(mntmNewMenuItem_1);
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		menuBar.add(mntmSair);
	}
	
	
	public JPanel confirmaReservaPanel(JFrame view) {
		view.getContentPane().remove(dinamicPanel);
        view.repaint();
        
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Confirmar Reserva", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
    	
		panel.setBounds(10, 55, 625, 285);
		frmReservaEventos.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Evento:");
		lblNewLabel.setBounds(22, 38, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Data:");
		lblNewLabel_1.setBounds(22, 79, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Horario:");
		lblNewLabel_2.setBounds(22, 122, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Qnt. Ingressos:");
		lblNewLabel_3.setBounds(22, 161, 82, 14);
		panel.add(lblNewLabel_3);
		
		qnt_Ingressofield = new JTextField();
		qnt_Ingressofield.setBounds(124, 158, 86, 20);
		panel.add(qnt_Ingressofield);
		qnt_Ingressofield.setColumns(10);
		
		eventofield = new JTextField();
		eventofield.setBounds(88, 35, 132, 20);
		panel.add(eventofield);
		eventofield.setColumns(10);
		eventofield.setText(nomeEvento.toString());
		
		
		datafield = new JTextField();
		datafield.setBounds(78, 76, 132, 20);
		panel.add(datafield);
		datafield.setColumns(10);
		datafield.setText(dataEvento.toString());
		
		horafield = new JTextField();
		horafield.setColumns(10);
		horafield.setBounds(78, 119, 132, 20);
		panel.add(horafield);
		horafield.setText(horaEvento.toString());
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double valor = 0;
				if(qnt_Ingressofield.getText() != null) {
					double quantidade = Double.parseDouble(qnt_Ingressofield.getText()) ;
					double total_ingressos = Double.parseDouble(n_ingressos.toString());
					double preco_ingresso = Double.parseDouble(""+preco);
					System.out.println(""+quantidade+""+total_ingressos +""+preco_ingresso);
					valor = quantidade*preco_ingresso;
					JOptionPane.showMessageDialog(null, "Evento: "+nomeEvento.toString() +"\t Reservado com sucesso para "+ textField_2.getText()+" \t Valor Total: "+valor, "Reserva Concluida", JOptionPane.INFORMATION_MESSAGE);
					dinamicPanel = listaEventosPanel(frmReservaEventos);
				}
			}
		});
		btnNewButton.setBounds(124, 230, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("sub. Total:");
		lblNewLabel_4.setBounds(346, 209, 66, 14);
		panel.add(lblNewLabel_4);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(422, 209, 152, 44);
		panel.add(textPane);
		
		JLabel lblNewLabel_5 = new JLabel("Nome:");
		lblNewLabel_5.setBounds(313, 38, 46, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("CPF:");
		lblNewLabel_6.setBounds(313, 79, 46, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Telefone:");
		lblNewLabel_7.setBounds(313, 108, 46, 14);
		panel.add(lblNewLabel_7);
		
		cpfField = new JTextField();
		cpfField.setBounds(369, 76, 152, 20);
		panel.add(cpfField);
		cpfField.setColumns(10);
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
		
		textField_1 = new JTextField();
		textField_1.setBounds(369, 105, 152, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(364, 35, 163, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		

		
		view.revalidate();
    	return panel;
	}
	
	public JPanel listaEventosPanel(JFrame view) {
		view.getContentPane().remove(dinamicPanel);
        view.repaint();
		JPanel listaPanel = new JPanel();
		listaPanel.setBounds(10, 64, 414, 161);
		frmReservaEventos.getContentPane().add(listaPanel);
		listaPanel.setLayout(null);
		
		table = new JTable();
		tableModel = new DefaultTableModel(
			    new Object[][] {
			    	{"Luan Santana vs. Ze Ramalho", "01/09/2023", "22:00", "1000", " 100.00"},
			    },
			    new String[] {
			        "Nome Evento", "Data", "Horario", "Ingressos Disponiveis", "Valor Ingresso"
			    }
			);
		table.setModel(tableModel);
		JTableHeader header = table.getTableHeader();
		header.setBounds(10, 0, 394, 20); // Posicione o cabeçalho conforme necessário
		listaPanel.add(header);
		table.setBounds(10, 21, 394, 129);
		listaPanel.add(table);
		table.getSelectionModel().addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting()) { 
		        int selectedRow = table.getSelectedRow();
		        nomeEvento = new Object();
		        dataEvento = new Object();
		        horaEvento = new Object();
		        n_ingressos = new Object();
		        preco = new Object();
		        if (selectedRow != -1) { // 
		        	nomeEvento = table.getValueAt(selectedRow, 0); 
		            dataEvento = table.getValueAt(selectedRow, 1);
		            horaEvento = table.getValueAt(selectedRow, 2);
		            n_ingressos = table.getValueAt(selectedRow, 3);
		            preco = table.getValueAt(selectedRow, 4);
		        }
		        
		    }
		});
		view.revalidate();
    	return listaPanel;
	}
}
