package reservas;

import javax.swing.table.DefaultTableModel;

public class Eventos_Disponiveis {
	
	public Eventos_Disponiveis() {}
	
	public static void addEventos(DefaultTableModel tableModel, String nome, String data, String horario, String num_ingressos, String valor) {
		
		tableModel.addRow(new Object[] {nome, data, horario, num_ingressos, valor});
	}
}
