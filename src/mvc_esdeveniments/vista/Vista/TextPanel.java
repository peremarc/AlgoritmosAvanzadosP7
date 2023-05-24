/**
 * Practica 7 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 21/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url 
 */
package mvc_esdeveniments.vista.Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import mvc_esdeveniments.model.Model;

public class TextPanel extends JPanel {
    JTextArea texto;
    
    public TextPanel(int width, int height, Model m, Vista v) {

        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(
                10, 5, 10, 10)); // Margen interno de 10 píxeles
        
        texto = new JTextArea();
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setEditable(false); // Para no poder editar el texto

        JScrollPane scrollPane = new JScrollPane(texto);
        this.add(scrollPane);
        this.setVisible(true);
    }
    
    public JTextArea getTextArea(){
        return texto;
    }
    
}
