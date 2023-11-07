package cad_produtos;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorTable extends DefaultTableCellRenderer {
    private Color evenColor = Color.WHITE;
    private Color oddColor = new Color(255, 255, 0); // Amarelo claro

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (!isSelected) {
            Color background = (row % 2 == 0) ? evenColor : oddColor;
            cell.setBackground(background);
        }

        return cell;
    }
}
