/**
 * Practica 7 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 21/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url
 */
package mvc_esdeveniments.vista.Vista;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import mvc_esdeveniments.MVC_Esdeveniments;

public class GraphPanel extends JPanel {

    MVC_Esdeveniments prog;
    /*Relación entre pìxels y las unidades de ambos ejes*/
    private double unitX, unitY;
    /*Margen para poner los datos de los ejes*/
    private final int margin = 70;

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
        int startX = 5+margin, startY = 10;
        int endX = this.getWidth() - margin, endY = this.getHeight() - margin;

        // Calculamos anchura y altura
        int width = endX - startX;
        int height = endY - startY;

        // Dibujamos el fondo blanco y el borde
        g.setColor(Color.WHITE);
        g.fillRect(startX, startY, endX, endY);
        g.setColor(Color.GRAY);
        g.drawRect(startX, startY, endX, endY);
        
        double denominadorN = prog.getModel().getNumero().get(prog.getModel().getNumero().size() - 1)-prog.getModel().getNumero().get(0);
        double denominadorT = Collections.max(prog.getModel().getTiempo())-Collections.min(prog.getModel().getTiempo());
        double incX = (double)width/denominadorN;
        double incY = (double) height / denominadorT;

        double x1 = startX;
        double y1 = startY + height - incY * (prog.getModel().getTiempo().get(0)-Collections.min(prog.getModel().getTiempo())); 
        for (int i = 1; i < prog.getModel().getTiempo().size(); i++) {
            double x2 =(prog.getModel().getNumero().get(i)-prog.getModel().getNumero().get(0))*incX+startX;
            double y2 = startY + height - incY * (prog.getModel().getTiempo().get(i)-Collections.min(prog.getModel().getTiempo()));
            g.drawLine((int)x1, (int) y1, (int)x2, (int)y2);
            x1 = x2;
            y1 = y2;
        }
        //Pintamos los ejes de coordenadas
        long max = Collections.max(prog.getModel().getTiempo());
        long maxNum = Collections.max(prog.getModel().getNumero());
        unitX = (endX - startX) / maxNum;
        unitY = ((double) (endY - startY) / (double) max);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        int divisionesX = 14;
        int divisionesY = 10;
        
        double resX = maxNum/divisionesX;
        double resY = (((endY-startY)/unitY)/1000000)/divisionesY;
        // Dibujamos los datos y divisores en el eje X
        for (int i = 0; i <= divisionesX; i++) { 
            g2d.drawString(Integer.toString((int)resX*i), 
                    startX+((i*(endX-startX))/divisionesX), endY+margin/2);
            g.drawLine(startX+((i*(endX-startX))/divisionesX), startY,
                    startX+((i*(endX-startX))/divisionesX), endY);
        }
        // Dibujamos los datos y divisores en el eje Y
        for (int i = 0; i <= divisionesY; i++) {
            g2d.drawString(Integer.toString((int)resY*i), 
                    startX-margin/2, endY-(i*(endY-startY))/divisionesY);
            g.drawLine(startX, endY-((i*(endY-startY))/divisionesY),
                    endX, endY-((i*(endY-startY))/divisionesY));
            
        }
        // Ponemos los títulos de los ejes y la gráfica
        g2d.drawString("Núm. factorizado", endX - width/2, endY + 55);
        g2d.drawString("Execution time (ms)", startX, startY + 30);
        g2d.drawString("Coste de factorización", margin+(endY-startY), startY - 30);


    }

}
