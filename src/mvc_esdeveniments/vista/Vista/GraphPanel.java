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
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import mvc_esdeveniments.model.Model;

public class GraphPanel extends JPanel {

    Model modelo;

    public GraphPanel(int width, int height, Model modelo, Vista v) {

        this.modelo = modelo;

        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(
                10, 5, 10, 10)); // Margen interno de 10 píxeles
        
        
        this.setVisible(true);
    }

    /**
     * Método que pinta el grafo con todos los nodos (idiomas) y sus aristas 
     * (distancias entre los idiomas), indicando con dos valores decimales sobre
     * la arista la distancia entre cada idioma.
     * 
     * @param g elemento que nos permite dibujar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
    

}
