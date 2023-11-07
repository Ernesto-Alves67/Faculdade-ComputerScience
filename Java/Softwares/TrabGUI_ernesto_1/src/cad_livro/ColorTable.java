package cad_livro;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorTable extends DefaultTableCellRenderer {
    private Color evenColor = Color.WHITE;
    private Color oddColor = new Color(255, 255, 0); // Amarelo claro
    private Color selColor = new Color(0,128,0);
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (!isSelected) {
            Color background = (column > 0) ? evenColor : oddColor; // Começar da segunda coluna (índice 1)
            cell.setBackground(background);
        } else {
            cell.setBackground(selColor); // Definir a cor verde para linhas selecionadas
        }

        return cell;
    }
}
