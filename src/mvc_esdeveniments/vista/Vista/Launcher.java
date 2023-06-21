/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_esdeveniments.vista.Vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import mvc_esdeveniments.MVC_Esdeveniments;
import mvc_esdeveniments.PerEsdeveniments;

/**
 *
 * @author jordo
 */
public class Launcher extends JFrame implements ActionListener, PerEsdeveniments{
    
    private final MVC_Esdeveniments prog;
    
    private JButton buttonPrimal;
    private JButton buttonRSA;
    
    private final int WIDTH = 500;
    private final int HEIGHT = 400;
    
    
    public Launcher(MVC_Esdeveniments prog){
        this.prog = prog;
        
        this.setSize(WIDTH, HEIGHT);
        this.setBackground(Color.LIGHT_GRAY);
        this.setTitle("Algoritmos Avanzados - Práctica 7");
        this.setResizable(false);
        
        initComponents();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initComponents() {
        this.setLayout(null);
        
        buttonPrimal = new JButton("Primalidad");
        buttonPrimal.setBounds(50, 50, WIDTH - 100, 100);
        buttonPrimal.addActionListener(this);
        this.add(buttonPrimal);
        
        buttonRSA = new JButton("Encriptación de Ficheros");
        buttonRSA.setBounds(50, 200, WIDTH - 100, 100);
        buttonRSA.addActionListener(this);
        
        this.add(buttonRSA);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(buttonPrimal)){
            prog.notificar("launch=primal");
        }
        
        if(e.getSource().equals(buttonRSA)){
            prog.notificar("launch=RSA");
        }
    }

    @Override
    public void notificar(String s) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
