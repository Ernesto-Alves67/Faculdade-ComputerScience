package cad_livro;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LayeredPaneExample {

    private JFrame frame;

    public static void main(String[] args) {
        LayeredPaneExample window = new LayeredPaneExample();
        window.frame.setVisible(true);
    }

    public LayeredPaneExample() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 434, 261);
        frame.getContentPane().add(layeredPane);

        JLabel backgroundImageLabel = new JLabel("");
        backgroundImageLabel.setBounds(0, 0, 434, 261);
        backgroundImageLabel.setIcon(new ImageIcon("background.jpg")); // Altere o caminho da imagem conforme necessário
        layeredPane.add(backgroundImageLabel, JLayeredPane.DEFAULT_LAYER);

        JButton btnButton = new JButton("Botão");
        btnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ação do botão
            }
        });
        btnButton.setBounds(163, 113, 89, 23);
        layeredPane.add(btnButton, JLayeredPane.PALETTE_LAYER);
    }
}

