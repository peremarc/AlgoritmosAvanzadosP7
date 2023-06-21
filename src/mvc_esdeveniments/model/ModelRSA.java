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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Paths;
import mvc_esdeveniments.control.PrimoProbable;

/**
 *
 * @author jfher
 */
public class ModelRSA implements Serializable{
    
    private BigInteger n, p, q;
    private BigInteger phiEuler;
    private BigInteger e, d;
    
    public ModelRSA(){
    }
    
    public BigInteger getN() {
        return n;
    }


    public void escribirClaves(String s, ModelRSA m) {
        try {
            String filePath = "files/" + s + ".dat";
            Files.createDirectories(Paths.get("files"));
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(m);
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero: " + e.getMessage());
        }
    }

    public ModelRSA leerClaves(String s) throws FileNotFoundException, IOException {
        ModelRSA modelo = null;
        try {
            String filePath = "files/" + s + ".dat";
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            modelo = (ModelRSA) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
        return modelo;
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
    
}
