/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_esdeveniments.vista.Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import mvc_esdeveniments.MVC_Esdeveniments;
import mvc_esdeveniments.MeuError;
import mvc_esdeveniments.PerEsdeveniments;


/**
 *
 * @author jordo
 */
public class RSAVista extends JFrame implements PerEsdeveniments{

    private final MVC_Esdeveniments prog;
    
    private JButton BGenKey;
    
    private JButton BSetFileToEncript;
    private JButton BClearFileEncript;
    
    private JButton BSetFileToDesEncript;
    private JButton BClearFileDesEncript;
    
    private JButton BEncriptar;
    private JButton BDesencriptar;
    
    private PanelTexto panelDesencriptado;
    private PanelTexto panelEncriptado;
    
    private JLabel FileEncripted;
    private JLabel FileToEncript;
    
    private final int WIDTH = 1200;
    private final int HEIGHT = 800;
    
    public RSAVista(MVC_Esdeveniments prog){
        this.prog = prog;
        initComponents();
    }
    
    private void initComponents() {
        this.setSize(WIDTH, HEIGHT);
        this.setBackground(Color.LIGHT_GRAY);
        this.setTitle("Algoritmos Avanzados - Práctica 7");
        
        this.setLayout(null);
        this.setResizable(false);

        // TITULO
        JLabel Titulo = new JLabel("Encriptacion / Desencriptación de Ficheros");
        Titulo.setFont(new Font("Britannic Bold", Font.BOLD, 15));
        Titulo.setHorizontalAlignment(0);
        Titulo.setBounds(getWidth() / 2 - 200, 10, 400, 30);
        this.add(Titulo);
        
        // PANEL ENCRIPTACION
        panelDesencriptado = new PanelTexto(50, 100, 500, 550);
        this.add(panelDesencriptado);
        
        BSetFileToEncript = new JButton("Seleccionar Fichero");
        BSetFileToEncript.setBounds(50, 50, 320, 30);
        this.add(BSetFileToEncript);
        
        BClearFileEncript= new JButton("Clear");
        BClearFileEncript.setBounds(400, 50, 150, 30);
        this.add(BClearFileEncript);
        
        FileToEncript = new JLabel("FILE NOT SELECTED");
        FileToEncript.setFont(new Font("Britannic Bold", Font.BOLD, 12));
        FileToEncript.setBounds(50, 650, 400, 30);
        this.add(FileToEncript);
                
        // PANEL DESENCRIPTACION
        panelEncriptado = new PanelTexto(650, 100, 500, 550);
        this.add(panelEncriptado);
        
        BSetFileToDesEncript = new JButton("Seleccionar Fichero");
        BSetFileToDesEncript.setBounds(650, 50, 320, 30);
        this.add(BSetFileToDesEncript);
        
        BClearFileDesEncript= new JButton("Clear");
        BClearFileDesEncript.setBounds(1000, 50, 150, 30);
        this.add(BClearFileDesEncript);

        FileEncripted = new JLabel("FILE NOT SELECTED");
        FileEncripted.setFont(new Font("Britannic Bold", Font.BOLD, 12));
        FileEncripted.setBounds(650, 650, 400, 30);
        this.add(FileEncripted);
        
        ActionListener actionListenerFiles = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf = new JFileChooser();
                jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jf.setCurrentDirectory(new File("files/"));
                /*Para que no nos permita seleccionar más de un archivo*/
                jf.setMultiSelectionEnabled(false);
                if (jf.showOpenDialog(jf) == JFileChooser.APPROVE_OPTION) {
                    /* Cojo el archivo seleccionado */
                    File selectedFile = jf.getSelectedFile();
                    /* Actualizamos los paneles a partir del fichero Seleccionado */
                    if(e.getSource().equals(BSetFileToEncript)){
                        panelDesencriptado.setFile(selectedFile);
                        panelDesencriptado.updateText();
                        FileToEncript.setText("File: " + selectedFile.getName()
                            + "   Size: " + selectedFile.length()
                        );
                        return;
                    }

                    panelEncriptado.setFile(selectedFile);
                    panelEncriptado.updateText();
                    FileEncripted.setText("File: " + selectedFile.getName()
                         + "   Size: " + selectedFile.length()); 
                }
            }
        };
        
        BSetFileToEncript.addActionListener(actionListenerFiles);
        BSetFileToDesEncript.addActionListener(actionListenerFiles);
        
        ActionListener actionListenerClear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(BClearFileEncript)){
                    panelDesencriptado.setFile(null);
                    panelDesencriptado.clearText();
                    FileToEncript.setText("FILE NOT SELECTED");
                    return;
                }
                
                if(e.getSource().equals(BClearFileDesEncript)){
                    panelEncriptado.setFile(null);
                    panelEncriptado.clearText();
                    FileEncripted.setText("FILE NOT SELECTED");
                }
        }};
        
        BClearFileEncript.addActionListener(actionListenerClear);
        BClearFileDesEncript.addActionListener(actionListenerClear);
        
        
        // GENERAR CLAVES
        BGenKey = new JButton("Generar Clave");
        BGenKey.setBounds(500, 700, 200, 50);
        this.add(BGenKey);
        
        // ENCRIPTAR FICHERO
        BEncriptar = new JButton("Encriptar Fichero");
        BEncriptar.setBounds(200, 700, 200, 50);
        this.add(BEncriptar);
        
        // DESENCRIPTAR FICHERO
        BDesencriptar = new JButton("Desencriptar Fichero");
        BDesencriptar.setBounds(800, 700, 200, 50);
        this.add(BDesencriptar);
        
        
        
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.revalidate();
        this.repaint();
        
    }
     
    @Override
    public void notificar(String s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }    
           

    protected class PanelTexto extends JPanel{
        
        private JTextArea textArea;
        private File FileRoute;
        
        public PanelTexto(int x, int y, int w, int h){
            this.setLayout(null);
            this.setBounds(x, y, w, h);
            this.setBackground(Color.BLACK);

            this.initComponents(w,h);
            
        }
        
        public File getFileRoute() {
            return FileRoute;
        }

        public void setFile(File FileRoute) {
            this.FileRoute = FileRoute;
        }

        private void initComponents(int w, int h) {
            this.textArea = new JTextArea();
            this.textArea.setEditable(false);
            this.textArea.setLineWrap(true);
            
            JScrollPane sp = new JScrollPane(textArea);
            sp.setSize(w, h);
            
            this.add(sp);
        }

        private void updateText() {
            this.textArea.setText("");
            try {
                this.textArea.append(FileUtils.readFileToString(this.FileRoute, "UTF-8"));
            } catch (IOException ex) {
                MeuError.informaError(ex);
                this.textArea.append("Internal error: IOException");
            }
        }

        private void clearText() {
            this.textArea.setText("");
        }
        
    }
    
}
