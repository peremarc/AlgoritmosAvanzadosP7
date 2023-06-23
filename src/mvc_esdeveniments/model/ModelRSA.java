/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_esdeveniments.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import mvc_esdeveniments.vista.Vista.Notification;

/**
 *
 * @author jfher
 */
public class ModelRSA implements Serializable{
    
    private BigInteger n, p, q;
    private BigInteger phiEuler;
    private BigInteger e, d;
    private boolean compactar;
    
    public ModelRSA(){
    }
    
    public void escribirClaves() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");  
        Date date = new Date();              
        String filePath = "keys/key_" + formatter.format(date) + ".dat";
        try {
            ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(filePath));
            objectOut.writeObject(this);
            objectOut.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
            new Notification("Internal Error: IOException");
            return;
        }
        new Notification("Key 'key_" + formatter.format(date) + ".dat' Generated");
    }

    public static ModelRSA leerClaves(String s){
        ModelRSA modelo = null;
        try {
            String filePath = "keys/" + s;
            ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(filePath));
            modelo = (ModelRSA) objectIn.readObject();
            objectIn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        
        return modelo;
    }
    
    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

    public BigInteger getPhiEuler() {
        return phiEuler;
    }

    public void setPhiEuler(BigInteger phiEuler) {
        this.phiEuler = phiEuler;
    }

    public BigInteger getE() {
        return e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }
    
    public boolean isCompactar() {
        return compactar;
    }

    public void setCompactar(boolean compactar) {
        this.compactar = compactar;
    }
    
}
