/**
 * Practica 7 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 21/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url
 */
package mvc_esdeveniments.vista.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.math.BigInteger;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import mvc_esdeveniments.MVC_Esdeveniments;
import mvc_esdeveniments.model.Model;

public class GraphPanel extends JPanel {

    MVC_Esdeveniments prog;

    public GraphPanel(int width, int height, MVC_Esdeveniments p) {

        this.prog = p;

        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(
                10, 5, 10, 10)); // Margen interno de 10 píxeles

        this.setVisible(true);
    }

    /**
     * Método que pinta el gráfico del coste asintótico de factorización.
     *
     * @param g elemento que nos permite dibujar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculamos coordenadas de inicio y fin del panel
        int startX = 5, startY = 32;
        int endX = this.getWidth() - 20, endY = this.getHeight() - 44;

        // Calculamos anchura y altura
        int width = endX - startX;
        int height = endY - startY;

        // Dibujamos el fondo blanco y el borde
        g.setColor(Color.WHITE);
        g.fillRect(startX, startY, endX, endY);
        g.setColor(Color.GRAY);
        g.drawRect(startX, startY, endX, endY);

        BigInteger denominadorN = prog.getModel().getNumero().get(prog.getModel().getNumero().size() - 1).subtract(prog.getModel().getNumero().get(0));
        double denominadorT = prog.getModel().getTiempo().get(prog.getModel().getTiempo().size() - 1)-prog.getModel().getTiempo().get(0);
        BigInteger incX = (new BigInteger(Integer.toString(width))).divide(denominadorN);
        System.out.println(new BigInteger(Integer.toString(width)));
        double incY = (double) height / denominadorT;

        int x1 = startX;
        double y1 = endY; 
        for (int i = 1; i < prog.getModel().getTiempo().size(); i++) {
            int x2 =Integer.parseInt((prog.getModel().getNumero().get(i).subtract(prog.getModel().getNumero().get(0))).multiply(incX).toString());
//            int x2 = Integer.parseInt(prog.getModel().getNumero().get(i).multiply(new BigInteger(Double.toString(incX))).toString());
            double y2 = height - incY * (prog.getModel().getTiempo().get(i)-prog.getModel().getTiempo().get(0));
            System.out.println("x1 " + x1 + " y1 " + y1 + " x2 " + x2 + " y2 " + y2);
            g.drawLine(x1, (int) y1, x2, (int)y2);
            x1 = x2;
            y1 = y2;

        }

      //  g.drawLine(startX, startY, endX, endY);

    }

}
