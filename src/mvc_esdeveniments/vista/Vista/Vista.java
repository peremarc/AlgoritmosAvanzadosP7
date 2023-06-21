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
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import mvc_esdeveniments.MVC_Esdeveniments;
import mvc_esdeveniments.MeuError;
import mvc_esdeveniments.PerEsdeveniments;

public class Vista extends JFrame implements ActionListener, PerEsdeveniments {

    private static final int PANEL_W = 1000, PANEL_H = 500;

    private MVC_Esdeveniments prog;

    private JMenuBar mb;
    private JMenu menu1, menu2;
    private JMenuItem mi1, mi2, mi3, mi4;

    private String option;

    private TextPanel textPanel;
    private GraphPanel graphPanel;

    private JDialog d;

//    JTextField numNxifres;
    public Vista(String s, MVC_Esdeveniments p) {
        super(s);
        prog = p;
        init();
    }

    /**
     * Inicializamos los componentes de control de la vista y ponemos un panel
     * vacío temporalmente.
     */
    private void init() {

        this.getContentPane().setLayout(new BorderLayout());
        option = "Text";
        mb = new JMenuBar();
        setJMenuBar(mb);
        menu2 = new JMenu("Mesurament");
        mb.add(menu2);
        mi4 = new JMenuItem("Obtenir ratio");
        mi4.addActionListener(this);
        menu2.add(mi4);
        menu1 = new JMenu("RSA");
        mb.add(menu1);
        mi1 = new JMenuItem("Generar claus");
        mi1.addActionListener(this);
        menu1.add(mi1);
        mi2 = new JMenuItem("Encriptar fitxer");
        mi2.addActionListener(this);
        menu1.add(mi2);
        mi3 = new JMenuItem("Desencriptar fitxer");
        mi3.addActionListener(this);
        menu1.add(mi3);

        JPanel botonera = new JPanel(new GridLayout(0, 1));
        JButton boto1 = new JButton("Generar num. N xifres");
        boto1.addActionListener(this);
        botonera.add(boto1);
        JButton boto2 = new JButton("Determinar primalitat");
        boto2.addActionListener(this);
        botonera.add(boto2);
        JButton boto3 = new JButton("Generar num. fort");
        boto3.addActionListener(this);
        botonera.add(boto3);
        JButton boto4 = new JButton("Factoritzar num. fort");
        boto4.addActionListener(this);
        botonera.add(boto4);
        JButton boto5 = new JButton("Graficar cost factorització");
        boto5.addActionListener(this);
        botonera.add(boto5);
        JButton boto6 = new JButton("Aturar gràfica");
        boto6.addActionListener(this);
        botonera.add(boto6);
//        JButton boto7 = new JButton("Reprèn gràfica");
//        boto7.addActionListener(this);
//        botonera.add(boto7);

        JPanel panelLateral = new JPanel(new GridLayout(2, 1));
//        panelLateral.add(xifres);
        panelLateral.add(botonera);
        this.add(BorderLayout.WEST, panelLateral);

        // Agregamos un margen interior de 10px al panel lateral
        panelLateral.setBorder(javax.swing.BorderFactory.createEmptyBorder(
                10, 10, 10, 5));

        pinta(); // Mostramos el panel de texto vacío por defecto

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(PANEL_W, PANEL_H));

    }

    public void mostrar() {
        this.pack();
        this.setVisible(true);
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            MeuError.informaError(e);
        }
        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comanda = e.toString();
        int a = comanda.indexOf(",cmd=") + 5;
        comanda = comanda.substring(a, comanda.indexOf(",", a));
        prog.notificar(comanda);
    }

    @Override
    public void notificar(String s) {
        if (s.contentEquals("Generar num. N xifres") && d == null) {
            // create a dialog Box
            d = new JDialog(this, "Núm. xifres");

            d.setLayout(new GridLayout(2, 1));

            JPanel xifres = new JPanel();
            xifres.setLayout(new GridLayout(0, 2));
            xifres.add(new JLabel("num. xifres"));
            JTextField nXifres = new JTextField();
            xifres.add(nXifres);
            d.add(xifres);

            JButton boto1 = new JButton("Acceptar");
            boto1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    if (!nXifres.getText().isEmpty()) {
                        d.dispose();
                        d = null;
                        prog.getControl().generarNumNXifres(Integer.parseInt(nXifres.getText()));
//                        numNxifres.setText(""+prog.getModel().getNumNXifres());
                        textPanel.getTextArea().setText("Número de " + nXifres.getText() + " xifres generat:\n" + prog.getModel().getNumNXifres());
                    }

                }

            });

            d.add(boto1);

            // setsize of dialog
            d.setSize(170, 100);

            // set visibility of dialog
            d.setVisible(true);
            d.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        } else if (s.contentEquals("Determinar primalitat")) {
            String aux = "";
            aux += "La primalidad del número " + prog.getModel().getNumNXifres() + " es " + prog.getControl().esPrimo(prog.getModel().getNumNXifres().toString());
            textPanel.getTextArea().setText(aux);
        } else if (s.contentEquals("Generar num. fort")) {
            prog.getControl().generarNumFort();
            String aux = "";
            aux += "El número fort generat és:\n" + prog.getModel().getNumDuro();
            textPanel.getTextArea().setText(aux);
        } else if (s.contentEquals("Factoritzar num. fort")) {
            prog.getControl().notificar("Factoritzar num. fort");
        } else if (s.contentEquals("Graficar cost factorització")) {
            option = "Graf";
            prog.getControl().notificar("Graficar cost factorització");
        } else if (s.contentEquals("pinta")) {
            pinta();
        } else if(s.contentEquals("Aturar gràfica")){
            prog.getControl().aturarGrafica();
        } else if(s.contentEquals("Reprèn gràfica")){
            prog.getControl().reprenGrafica();
        }
    }

    public TextPanel getTextPanel() {
        return textPanel;
    }

    /**
     * Cambia entre los distintos paneles según el display seleccionado.
     */
    public void pinta() {

        // Borramos los paneles antes de crear el nuevo
        if (textPanel != null) {
            this.getContentPane().remove(textPanel);
        }
        if (graphPanel != null) {
            this.getContentPane().remove(graphPanel);
        }

        switch (option) {
            case "Text":
                textPanel = new TextPanel(PANEL_W, PANEL_H, this.prog.getModel(), this);
                this.add(textPanel);
                break;
            case "Graf":
                graphPanel = new GraphPanel(PANEL_W, PANEL_H, prog);
                this.add(graphPanel);
                break;
            default:
                break;
        }
        this.pack();

    }

}